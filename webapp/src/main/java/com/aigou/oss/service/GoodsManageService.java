package com.aigou.oss.service;

import com.aigou.oss.config.Config;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoBrandDAO;
import com.aigou.oss.dao.GoCategoryDAO;
import com.aigou.oss.dao.GoShopcodes1DAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoBrand;
import com.aigou.oss.model.GoCategory;
import com.aigou.oss.model.GoShopcodes1;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.model.dto.ShopImg;
import com.aigou.oss.model.vo.*;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import com.aigou.oss.util.el.ElBase;
import org.apache.commons.httpclient.HttpClient;
import org.apache.ibatis.session.RowBounds;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/7/7.
 *  * 模块9-商品管理
 */
@Service
public class GoodsManageService {

    @Autowired
    private GoShoplistDAO goShoplistDAO;
    @Autowired
    private GoBrandDAO goBrandDAO;
    @Autowired
    private GoCategoryDAO goCategoryDAO;
    @Autowired
    private GoShopcodes1DAO goShopcodes1DAO;


    /**
     * 从网页上爬 商品 数据
     * @param request
     * @param url
     * @param site
     * @return
     */
    public GoShoplist parseHtmlToGetData (HttpServletRequest request, String url, String site) throws IOException {

        GoShoplist shop = null;
        if ("jd".equals(site)) {    //京东

            String browserUrl = "http://item.jd.com/"+url +".html";

            shop = new GoShoplist();

            URL ur = new URL(browserUrl);
            Document doc = Jsoup.parse(ur,10000);
            Element bod = doc.getElementById("name");
            String title = "";
            if (bod != null) {
                title = bod.text();
            }
            if (title == ""){
               List<Element> bod2 = doc.getElementsByAttributeValue("class","sku-name");
               if(bod2 != null && bod2.size() >0) {
                   Element tit = bod2.get(0);
                   title = tit.text();
               }
            }

            shop.setTitle(title);
            shop.setKeywords(title);
            shop.setDescription("京东商城提供正品"+title+",欢迎前来"+title+",省钱又放心");

            shop.setYunjiage(new BigDecimal(1));
            shop.setMaxqishu(100);

            //详情:网页上源代码中没有,手机版上源代码中有
            //网页版:http://item.jd.com/1856581.html
            //手机版:http://item.m.jd.com/detail/1856581.html
            String mUrl = "http://item.m.jd.com/detail/"+url+".html";
            URL mUr = new URL(mUrl);
            Document mDoc = Jsoup.parse(mUr,10000);
            Element con = mDoc.getElementById("scale-cont");

            String content = null;
            if (con != null) {
                content = con.html();
            }
            shop.setContent(content);

            return  shop;

        }else if ("aigou".equals(site)){

        }

        return shop;
    }


    /**
     * 获取对应的cateId的品牌
     * @param request
     * @param cateId
     * @return
     */
    public List<GoBrand> getBrandByCateId (HttpServletRequest request, Integer cateId) {

        return goBrandDAO.getBrandByCateId (cateId);
    }

    /**
     * 新增商品
     * @param request
     * @param newG
     * @return
     */
    public String doSaveGoods (HttpServletRequest request,AddNewGoodsVo newG) throws Exception {

        GoShoplist shop = new GoShoplist();
        shop.setCateid(newG.getCateId());
        shop.setBrandid(0);
        shop.setTitle(newG.getTitle());
        shop.setTitle2(newG.getTitle2());
        shop.setKeywords(newG.getKeywords());
        shop.setDescription(newG.getDescription());
        shop.setMoney(newG.getMoney());
        shop.setYunjiage(newG.getYunjiage());
        shop.setMaxqishu(newG.getMaxqishu());
        shop.setQuyuBegin(newG.getQuyuBegin());
        shop.setQuyuEnd(newG.getQuyuEnd());
        shop.setThumb(newG.getThumb());
        shop.setPos(newG.getPos());
        shop.setRenqi(newG.getRenqi());
        shop.setSid(0);
        //默认的值
        shop.setOrder(1);
        shop.setQShowtime("N");
        shop.setRenqipos(0);
        shop.setNewpos(0);
        shop.setBannershop(0);
        shop.setPosthumb("");
        shop.setQuyu(0);

        String picarrStr = newG.getPicarr();
        if (picarrStr.startsWith(";")){
            picarrStr = picarrStr.replaceFirst(";","");
        }

        String[] pics = picarrStr.split(";");
        String picSer = ShopImg.getSerialize(pics);

        shop.setPicarr(picSer);

        BigDecimal money = newG.getMoney();
        //总人数就是总价格
        Integer zongrenshu = money.intValue();
        shop.setZongrenshu(zongrenshu);
        shop.setCanyurenshu(0);
        shop.setShenyurenshu(zongrenshu);
        shop.setQishu(1);
        shop.setTime((int)(System.currentTimeMillis()/1000));

        shop.setContent(newG.getContent());

        goShoplistDAO.insert(shop);

        Integer id = shop.getId();

        shop.setSid(id);

        goShoplistDAO.updateByPrimaryKey(shop);

        //生成对应的云购码
        createCodesByZonRenShu (shop);


        return JsonUtil.buildSuccess();
    }

    /**
     * 生成云购码    新增商品,修改商品的时候,总人数就是总价格
     * @param item
     * @throws Exception
     */
    public void createCodesByZonRenShu(GoShoplist item) throws Exception {

        // 生成云购码
        int countNum = item.getZongrenshu();
        int len = 3000;

        int num = countNum / len + 1;
        int code_i = countNum;

        if (num == 1) {

            List<Integer> list = new ArrayList<>();

            for (int i = 1; i <= countNum; i++) {
                list.add(10000000 + i);
            }

            String code = ShopCode.getSerialize(list);

            GoShopcodes1 codes = new GoShopcodes1();
            codes.setSId(item.getId());
            codes.setSCid(1);
            codes.setSLen(countNum);
            codes.setSCodes(code);
            codes.setSCodesTmp(code);

            goShopcodes1DAO.insert(codes);

        } else {

            for (int i = 1; i < num; i++) {

                List<Integer> list = new ArrayList<>();

                for (int j = 1; j <= len; j++) {

                    list.add(10000000 + code_i);
                    code_i--;
                }

                String code = ShopCode.getSerialize(list);

                GoShopcodes1 codes = new GoShopcodes1();
                codes.setSId(item.getId());
                codes.setSCid(i);
                codes.setSLen(len);
                codes.setSCodes(code);
                codes.setSCodesTmp(code);

                goShopcodes1DAO.insert(codes);

            }

            countNum = countNum - ((num - 1) * len);

            List<Integer> list = new ArrayList<>();

            for (int i = 1; i <= countNum; i++) {
                list.add(10000000 + code_i);
                code_i--;
            }

            String code = ShopCode.getSerialize(list);

            GoShopcodes1 codes = new GoShopcodes1();
            codes.setSId(item.getId());
            codes.setSCid(num);
            codes.setSLen(countNum);
            codes.setSCodes(code);
            codes.setSCodesTmp(code);

            goShopcodes1DAO.insert(codes);

        }
    }


    /**
     * 商品列表
     * @param request
     * @param pageSize  商品列表处用户想显示的每页行数
     * @return
     */
    public List<GoodsListVo> getGoodsList(HttpServletRequest request,Integer pageSize,Integer pageNum,Integer status,String title) throws Exception {

        int offSet = (pageNum - 1) * pageSize;
        RowBounds bounds = new RowBounds(offSet, pageSize);

        List<GoShoplist> shoplists = goShoplistDAO.getGoodsListByStatus(status,title,bounds);
        List<GoodsListVo> vos = new ArrayList<>(shoplists.size());

        for (GoShoplist shop : shoplists) {
            GoodsListVo vo = turnGoShoplistToGoodsListVo(shop);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 品牌列表
     * @param request
     * @param pageSize  商品列表处用户想显示的每页行数
     * @return
     */
    public List<BrandListVo> getBrandList(HttpServletRequest request, Integer pageSize, Integer pageNum) throws Exception {

        int offSet = (pageNum - 1) * pageSize;
        RowBounds bounds = new RowBounds(offSet, pageSize);

        List<GoBrand> brands = goBrandDAO.getAllBrandList(bounds);
        List<BrandListVo> vos = new ArrayList<>(brands.size());

        for (GoBrand brand : brands) {
            BrandListVo vo = turnGoBrandToBrandListVo(brand);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 实体类转换
     * @param brand
     * @return
     */
    public BrandListVo turnGoBrandToBrandListVo (GoBrand brand) {
        BrandListVo vo = new BrandListVo();

        vo.setId(brand.getId());
        String cateId = brand.getCateid();
        if (!StringUtil.isEmpty(cateId)) {
            String[] cates = cateId.split(",");
            List<Integer> cateIds = new ArrayList<>(cates.length);
            List<String>  cateNames = new ArrayList<>(cates.length);
            List<GoCategory> idToName = new ArrayList<>();
            for (int i = 0;i<cates.length ; i++){
                Integer cateid = Integer.valueOf(cates[i]);
                cateIds.add(cateid);
                String cateName = null;
                GoCategory category = goCategoryDAO.selectByPrimaryKey(cateid);
                if (category !=null){
                    cateName = category.getName();
                }
                if (category == null) {
                    cateName = "不存在";
                }
                cateNames.add(cateName);
                GoCategory cate = new GoCategory();
                cate.setCateid(cateid);
                cate.setName(cateName);
                idToName.add(cate);
            }
            vo.setCateIds(cateIds);
            vo.setCateNames(cateNames);
            vo.setIdToName(idToName);
        }
        String name = brand.getName();
        if (StringUtil.isEmpty(name)) {
            name = "";
        }
        vo.setName(name);
        Integer order = brand.getOrder();
        if (order == null) {
            order = 0;
        }
        vo.setOrder(order);

        return vo;
    }
    /**
     * 实体类转换
     * @param shop
     * @return
     */
    public GoodsListVo turnGoShoplistToGoodsListVo(GoShoplist shop) throws Exception {

        GoodsListVo vo = new GoodsListVo();

        vo.setId(shop.getId());
        vo.setSid(shop.getSid());
        String title = shop.getTitle();
        if (StringUtil.isEmpty(title)) {
            title = "";
        }
        vo.setTitle(title);
        String title2 = shop.getTitle2();
        if (StringUtil.isEmpty(title2)) {
            title2 = "";
        }
        vo.setTitle2(title2);
        String keywords = shop.getKeywords();
        if (StringUtil.isEmpty(keywords)) {
            keywords = "";
        }
        vo.setKeywords(keywords);
        String description = shop.getDescription();
        if (StringUtil.isEmpty(description)) {
            description = "";
        }
        vo.setDescription(description);
        vo.setQuyuBegin(shop.getQuyuBegin());
        vo.setQuyuEnd(shop.getQuyuEnd());
        String picarr = shop.getPicarr();
        if (StringUtil.isEmpty(picarr)) {
            picarr = "";
        }
        vo.setPicarr(picarr);
        if (!StringUtil.isEmpty(picarr)) {

            List<String > pics = getAllPicarr(picarr);
            vo.setPics(pics);
        }
        vo.setPos(shop.getPos());
        vo.setRenqi(shop.getRenqi());
        Integer xsjxTime = shop.getXsjxTime();
        if (xsjxTime != null) {
            String xsjxTimeShow = ElBase.ftm10Time(xsjxTime);
            vo.setXsjxTimeShow(xsjxTimeShow);
        }
        String  content = shop.getContent();
        if (StringUtil.isEmpty(content)) {
            content = "";
        }
        if (!StringUtil.isEmpty(content)){
            content = content.replaceAll("\"","\'");
        }
        vo.setContent(content);
        BigDecimal price = shop.getMoney();
        if (price == null){
            price = new BigDecimal(0);
        }
        vo.setMoney(price);
        BigDecimal yungoujia = shop.getYunjiage();
        if (yungoujia == null){
            yungoujia = new BigDecimal(0);
        }
        vo.setYunjiage(yungoujia);

        Integer qishu = shop.getQishu();
        if (qishu != null) {
            vo.setQishu(qishu);
        }
        Integer maxqishu = shop.getMaxqishu();
        if (maxqishu == null) {
            maxqishu = 0;
        }
        vo.setMaxqishu(maxqishu);
        String thumb = shop.getThumb();
        if (StringUtil.isEmpty(thumb)) {
            thumb = "";
        }
        if (!thumb.startsWith("http://")) {
            thumb = Config.getImageBaseUrl()+thumb;
        }
        vo.setThumb(thumb);

        vo.setCateid(shop.getCateid());
        vo.setBrandid(shop.getBrandid());

        Integer quid = shop.getQUid();
        if (quid == null){
            vo.setStatus(1);
            vo.setStatusShow("进行中");
        }
        if (quid != null){
            vo.setStatus(0);
            vo.setStatusShow("已结束");
        }


        return vo;
    }

    /**
     * 把数据库中序列化 的多张图片分开
     * @param picarr
     * @return
     */
    public List<String> getAllPicarr (String picarr) throws Exception {

        List<String> picas = ShopImg.getUnserializeList(picarr);
        List<String> picass = new ArrayList<>(picas.size());
        for (String pic : picas) {
            if (!pic.startsWith("http://")){
                pic = Config.getImageBaseUrl()+pic;
            }
            picass.add(pic);
        }
        return picass;
    }

    /**
     * 商品列表总条数 用于分页
     * @param request
     * @return
     */
    public Integer getGoodsListTotalNum(HttpServletRequest request,Integer status,String title) {

        return goShoplistDAO.getGoodsListByStatusTotalNum(status,title);

    }

    /**
     * 品牌列表总条数 用于分页
     * @param request
     * @return
     */
    public Integer getBrandListTotalNum(HttpServletRequest request) {

        return goBrandDAO.getBrandListTotalNum();

    }

    /**
     * 要删除商品id的用&连接的字符串
     * @param request
     * @param ids
     * @return
     */
    public String doDeleteGoods(HttpServletRequest request,String ids ) {

        if (StringUtil.isEmpty(ids)){
            return JsonUtil.buildError("请选择要删除的商品");
        }
        String[] idsStr = ids.split("&");
        int n = 0;
        for (int i=0 ;i <idsStr.length ;i++ ) {
            Integer id = Integer.valueOf(idsStr[i]);
            GoShoplist shop = goShoplistDAO.selectByPrimaryKey(id);
            if (shop == null) {
                return JsonUtil.buildError("第"+(i+1)+"件商品不存在");
            }
            goShoplistDAO.deleteByPrimaryKey(id);
            n++;
        }
        return JsonUtil.buildSuccess("成功删除"+n+"件商品");
    }

    /**
     * 带回到修改页面的数据
     * @param request
     * @param id
     * @return
     */
    public GoodsListVo getGoodsVoById (HttpServletRequest request,Integer id) throws Exception {

        GoShoplist shop = goShoplistDAO.selectByPrimaryKey(id);

        return turnGoShoplistToGoodsListVo(shop);
    }

    /**
     * 修改商品
     * @param request
     * @param newG
     * @return
     */
    public String doUpdateGoods (HttpServletRequest request,AddNewGoodsVo newG) throws Exception {

        Integer sid = newG.getSid();
        GoShoplist lastShop = goShoplistDAO.selectLastShop(sid);

        BigDecimal newMoney = newG.getMoney();
        BigDecimal newYungoujia = newG.getYunjiage();

        BigDecimal oldMoney = lastShop.getMoney();
        BigDecimal oldYungoujia = lastShop.getYunjiage();
        //如果钱没变,则是正常的修改商品
        if (newMoney.intValue() == oldMoney.intValue() && newYungoujia.intValue() == oldYungoujia.intValue()){

            return normalUpdate(request,newG,lastShop);
        }
        //如果钱有变,则是增加修改商品
        if (newMoney.intValue() != oldMoney.intValue() || newYungoujia.intValue() != oldYungoujia.intValue()){

            return moneyUpdate(request,newG,lastShop);
        }
        return null;
    }

    /**
     * 普通修改商品
     * @param request
     * @param newG      修改之后的数据
     * @param lastShop  修改之前的商品
     * @return
     */
    public String normalUpdate(HttpServletRequest request,AddNewGoodsVo newG,GoShoplist lastShop) throws Exception {


        lastShop.setCateid(newG.getCateId());
        lastShop.setBrandid(0);
        lastShop.setTitle(newG.getTitle());
        lastShop.setTitle2(newG.getTitle2());
        lastShop.setKeywords(newG.getKeywords());
        lastShop.setDescription(newG.getDescription());
        lastShop.setMaxqishu(newG.getMaxqishu());
        lastShop.setThumb(newG.getThumb());
        String picarrStr = newG.getPicarr();
        if (picarrStr.startsWith(";")){
            picarrStr = picarrStr.replaceFirst(";","");
        }
        String[] pics = picarrStr.split(";");
        String picSer = ShopImg.getSerialize(pics);
        lastShop.setPicarr(picSer);
        lastShop.setRenqi(newG.getRenqi());
        lastShop.setQuyuBegin(newG.getQuyuBegin());
        lastShop.setQuyuEnd(newG.getQuyuEnd());

        goShoplistDAO.updateByPrimaryKeySelective(lastShop);

        return JsonUtil.buildSuccess();
    }

    /**
     * 修改 总价格 及 云购价 时新增一期商品 sid不变
     * @param request
     * @param newG
     * @param lastShop
     * @return
     * @throws Exception
     */
    public String moneyUpdate(HttpServletRequest request,AddNewGoodsVo newG,GoShoplist lastShop) throws Exception {

        GoShoplist shop = new GoShoplist();

        shop.setSid(lastShop.getSid());
        Integer maxqishu = newG.getMaxqishu();
        Integer qishu = lastShop.getQishu();
        if (qishu>=maxqishu){
            return JsonUtil.buildError("请增加最大期数");
        }
        shop.setMaxqishu(maxqishu);
        shop.setQishu(qishu+1);

        shop.setCateid(newG.getCateId());
        shop.setBrandid(0);
        shop.setTitle(newG.getTitle());
        shop.setTitleStyle(lastShop.getTitleStyle());
        shop.setTitle2(newG.getTitle2());
        shop.setKeywords(newG.getKeywords());
        shop.setDescription(newG.getDescription());

        BigDecimal money = newG.getMoney();
        BigDecimal yungoujia = newG.getYunjiage();
        shop.setMoney(money);
        shop.setYunjiage(yungoujia);
        Integer zongrenshu = money.intValue();
        shop.setZongrenshu(zongrenshu);
        shop.setCanyurenshu(0);
        shop.setShenyurenshu(zongrenshu);
        shop.setDefRenshu(lastShop.getDefRenshu());
        shop.setThumb(newG.getThumb());
        String picarrStr = newG.getPicarr();
        if (picarrStr.startsWith(";")){
            picarrStr = picarrStr.replaceFirst(";","");
        }
        String[] pics = picarrStr.split(";");
        String picSer = ShopImg.getSerialize(pics);
        shop.setPicarr(picSer);

        String content = lastShop.getContent();
        shop.setContent(content);
        shop.setCodesTable(lastShop.getCodesTable());
        shop.setRenqi(newG.getRenqi());
        shop.setTime((int)(System.currentTimeMillis()/1000));

        //默认的值
        shop.setOrder(1);
        shop.setQShowtime("N");
        shop.setRenqipos(0);
        shop.setNewpos(0);
        shop.setBannershop(0);
        shop.setPosthumb("");
        shop.setQuyu(0);

        shop.setQuyuBegin(newG.getQuyuBegin());
        shop.setQuyuEnd(newG.getQuyuEnd());

        //生成对应的云购码
        goShoplistDAO.insert(shop);
        createCodesByZonRenShu (shop);

        return JsonUtil.buildSuccess();
    }

    /**
     * 商品详情
     * @param request
     * @param id
     * @return
     */
    public GoodsDetailVo getGoodsDetailById (HttpServletRequest request,Integer id) throws Exception {

        GoShoplist shoplist = goShoplistDAO.selectByPrimaryKey(id);

        return turnGoShoplistToGoodsDetailVo(shoplist);
    }

    /**
     * 实体类转换
     * @param shop
     * @return
     */
    public GoodsDetailVo turnGoShoplistToGoodsDetailVo (GoShoplist shop) throws Exception {

        GoodsDetailVo vo = null;

        if (shop != null) {

            vo = new GoodsDetailVo();

            vo.setShopId(shop.getId());
            String title = shop.getTitle();
            if (StringUtil.isEmpty(title)) {
                title = "";
            }
            vo.setTitle(title);
            String  title2 = shop.getTitle2();
            if (StringUtil.isEmpty(title2)) {
                title2 = "";
            }
            vo.setTitle2(title2);
            String content = shop.getContent();
            if (StringUtil.isEmpty(content)){
                content = "";
            }
            vo.setContent(content);

            String picarr = shop.getPicarr();
            if (StringUtil.isEmpty(picarr)) {
                picarr = "";
            }
            vo.setPicarr(picarr);
            if (!StringUtil.isEmpty(picarr)) {
                List<String> pics1 = ShopImg.getUnserializeList(picarr);
                List<String > pics = new ArrayList<>(pics1.size());
                for (String pic : pics1) {
                    if (!StringUtil.isEmpty(pic)) {
                        if (!pic.startsWith("http://")) {
                            pic = Config.getImageBaseUrl() + pic;
                        }
                    }
                    pics.add(pic);
                }
                vo.setPics(pics);
            }
        }
        return vo;
    }

    /**
     * 删除的品牌id用
     * @param request
     * @param brandIds
     * @return
     */
    public String doDeleteBrand (HttpServletRequest request,String brandIds){


        if (StringUtil.isEmpty(brandIds)){
            return JsonUtil.buildError("请选择要删除的品牌纪录");
        }
        String[] idsStr = brandIds.split("&");
        int n = 0;
        for (int i=0 ;i <idsStr.length ;i++ ) {
            Integer id = Integer.valueOf(idsStr[i]);
            GoBrand brand = goBrandDAO.selectByPrimaryKey(id);
            if (brand == null) {
                return JsonUtil.buildError("第"+(i+1)+"个品牌不存在");
            }
            goBrandDAO.deleteByPrimaryKey(id);
            n++;
        }
        return JsonUtil.buildSuccess("成功删除"+n+"个品牌纪录");
    }

    /**
     * 由品牌id获取信息
     * @param request
     * @param brandId
     * @return
     */
    public BrandListVo getBrandRecordById(HttpServletRequest request,Integer brandId) {

        GoBrand brand = goBrandDAO.selectByPrimaryKey(brandId);
        BrandListVo vo = null;
        if (brand != null) {
            vo = turnGoBrandToBrandListVo(brand);
        }
        return vo;
    }

    /**
     * 修改品牌信息
     * @param request
     * @param cateIds
     * @param brandId
     * @param brandName
     * @param brandOrder
     * @return
     */
    public String doUpdateBrandWithData (HttpServletRequest request,String cateIds,Integer brandId,String brandName,Integer brandOrder){


        GoBrand brand = goBrandDAO.selectByPrimaryKey(brandId);
        if (brand == null) {
            return JsonUtil.buildError("该品牌不存在");
        }
        brand.setId(brandId);
        brand.setCateid(cateIds);
        brand.setName(brandName);
        brand.setOrder(brandOrder);

        goBrandDAO.updateByPrimaryKey(brand);

        return JsonUtil.buildSuccess();
    }

    /**
     * 添加品牌信息
     * @param request
     * @param cateIds
     * @param brandName
     * @param brandOrder
     * @return
     */
    public String doAddBrandWithData (HttpServletRequest request,String cateIds,String brandName,Integer brandOrder){


        GoBrand brand = new GoBrand();

        brand.setCateid(cateIds);
        brand.setName(brandName);
        brand.setOrder(brandOrder);
        brand.setStatus("Y");

        goBrandDAO.insertSelective(brand);

        return JsonUtil.buildSuccess();
    }

    /**
     * 加载 栏目 信息
     * @param request
     * @return
     */
    public List<CateParentWithSonVo> getCateParentWithSonVo(HttpServletRequest request) {

        List<CateParentWithSonVo> vos = new ArrayList<>();

        //先找到所有的有 parentid的栏目
        List<GoCategory> parentCate = goCategoryDAO.getParentCate();
        //再找到子栏目
        for (GoCategory parent : parentCate) {

            CateParentWithSonVo parentWithSonVo = new CateParentWithSonVo();

            Integer parentId = parent.getCateid();
            List<GoCategory> sonCate = goCategoryDAO.getSonCateByParentCateId(parentId);

            parentWithSonVo.setParent(parent);
            parentWithSonVo.setSon(sonCate);

            vos.add(parentWithSonVo);
        }
        return vos;
    }
}
