package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.AgCategory;
import com.aigou.oss.model.GoBrand;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.model.vo.*;
import com.aigou.oss.service.CategoryService;
import com.aigou.oss.service.GoodsManageService;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/7/7.
 *
 * 模块9-商品管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/goods",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class GoodsManageController {


    @Autowired
    private GoodsManageService goodsManageService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 跳转到 添加商品 的页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addNewGoodsPage")
    public String gotoGoodsManagePage (HttpServletRequest request) {

        List<AgCategory> categories = categoryService.getAllCategory(request);

        request.setAttribute("vo",categories);

        return "goods_manage";
    }

    /**
     * 从网页上爬 商品 数据
     * @param request
     * @param url
     * @param site
     * @return
     */
    @RequestMapping(value = "/parseHtml")
    @ResponseBody
    public String parseHtmlToGetData (HttpServletRequest request,
                                      @RequestParam(value = ParamConsts.url,required = true) String url,
                                      @RequestParam(value = ParamConsts.site,defaultValue = "jd") String site) throws IOException {

        GoShoplist shop = goodsManageService.parseHtmlToGetData(request,url,site);

        Map<String,Object> map = new HashMap<>(1);
        map.put("shop",shop);

        return JsonUtil.buildData(map);
    }
    /**
     * 新增商品
     * @param request
     * @param newGoods
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveGoods(HttpServletRequest request,AddNewGoodsVo newGoods) throws Exception {

        return goodsManageService.doSaveGoods(request,newGoods);
    }

    /**
     * 点击分类得到想要的品牌
     * @param request
     * @param cateId
     * @return
     */
    @RequestMapping(value = "/getBrand")
    @ResponseBody
    public String getBrand (HttpServletRequest request,@RequestParam(value = ParamConsts.cateId,required = true)Integer cateId) {

        List<GoBrand> brand = goodsManageService.getBrandByCateId (request,cateId);
        Map<String,Object> map = new HashMap<>(1);
        map.put("brand",brand);

        return JsonUtil.buildData(map);
    }

    /**
     * 跳转到商品列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goodsList")
    public String goodsList (HttpServletRequest request) {

        return "goods_list";
    }

    /**
     * 商品列表
     * @param request
     * @param pageSize  商品列表处用户想显示的每页行数
     * @return
     */
    @RequestMapping(value = "/listByPageSize")
    @ResponseBody
    public String listByPageSize (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.pageSize,defaultValue = "15") Integer pageSize,
                                  @RequestParam(value = ParamConsts.pageNum,defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = ParamConsts.status,defaultValue = "1") Integer status,
                                  @RequestParam(value = ParamConsts.title,defaultValue = "")String title) throws Exception {

        if (StringUtil.isEmpty(title)){
            title = null;
        }

        List<GoodsListVo> vos = goodsManageService.getGoodsList(request,pageSize,pageNum,status,title);

        Integer total = goodsManageService.getGoodsListTotalNum(request,status,title);
        Integer totalPage = 1;
        if (total % pageSize == 0) {
            totalPage = total / pageSize ;
        }
        if (total % pageSize != 0) {
            totalPage = total / pageSize +1 ;
        }

        Map<String,Object> map = new HashMap<>(2);

        map.put("list",vos);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     * 要删除商品id的用&连接的字符串
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteGoods")
    @ResponseBody
    public String deleteGoods (HttpServletRequest request,
                               @RequestParam(value = ParamConsts.goodsIds,defaultValue = "") String ids) {

        return goodsManageService.doDeleteGoods (request,ids);
    }

    /**
     * 修改商品页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateGoodsPage")
    public String gotoUpdateGoodsPage (HttpServletRequest request,
                                       @RequestParam(value = ParamConsts.goodsId,required = true) Integer id) throws Exception {

        GoodsListVo vo = goodsManageService.getGoodsVoById (request,id);

        Integer cateId = vo.getCateid();
        List<GoBrand> brand = goodsManageService.getBrandByCateId (request,cateId);

        List<AgCategory> categories = categoryService.getAllCategory(request);

        request.setAttribute("categories",categories);

        request.setAttribute("brand",brand);
        request.setAttribute("vo",vo);

        return "update_goods";
    }

    /**
     * 修改商品
     * @param request
     * @param newGoods
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateGoods(HttpServletRequest request,AddNewGoodsVo newGoods) throws Exception {

        return goodsManageService.doUpdateGoods(request,newGoods);
    }

    /**
     * 商品详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/goodsDetailPage")
    public String gotoGoodsDetailPageWithData (HttpServletRequest request,
                                               @RequestParam(value = ParamConsts.goodsId,required = true) Integer id) throws Exception {

        GoodsDetailVo vo = goodsManageService.getGoodsDetailById (request,id);
        request.setAttribute("vo",vo);

        return "goods_detail";
    }


    /**
     * 跳转到品牌列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/brandList")
    public String brandList (HttpServletRequest request) {

        return "brand_list";
    }

    /**
     * 品牌列表
     * @param request
     * @param pageSize  品牌列表处用户想显示的每页行数
     * @return
     */
    @RequestMapping(value = "/brandListByPageSize")
    @ResponseBody
    public String brandListByPageSize (HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.pageSize,defaultValue = "15") Integer pageSize,
                                  @RequestParam(value = ParamConsts.pageNum,defaultValue = "1")Integer pageNum) throws Exception {

        List<BrandListVo> vos = goodsManageService.getBrandList(request,pageSize,pageNum);

        Integer total = goodsManageService.getBrandListTotalNum(request);
        Integer totalPage = 1;
        if (total % pageSize == 0) {
            totalPage = total / pageSize ;
        }
        if (total % pageSize != 0) {
            totalPage = total / pageSize +1 ;
        }

        Map<String,Object> map = new HashMap<>(2);

        map.put("list",vos);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     * 删除的品牌id用&连接
     * @param request
     * @param brandIds
     * @return
     */
    @RequestMapping(value = "/deleteBrand")
    @ResponseBody
    public String deleteBrandRecord (HttpServletRequest request,
                                     @RequestParam(value = ParamConsts.brandIds,defaultValue = "")String brandIds) {

        return goodsManageService.doDeleteBrand (request,brandIds);
    }

    /**
     * 跳转到修改品牌页面
     * @param request
     * @param brandId
     * @return
     */
    @RequestMapping(value = "/updateBrandPage")
    public String updateBrandPage (HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.brandId,required = true)Integer brandId) {

        BrandListVo vo = goodsManageService.getBrandRecordById (request,brandId);

        request.setAttribute("vo",vo);

        return "update_brand";
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
    @RequestMapping(value = "/updateBrand")
    @ResponseBody
    public String updateBrand (HttpServletRequest request,
                               @RequestParam(value = ParamConsts.cateIds,defaultValue = "") String cateIds,
                               @RequestParam(value = ParamConsts.brandId,required = true)Integer brandId,
                               @RequestParam(value = ParamConsts.brandName,defaultValue = "") String brandName,
                               @RequestParam(value = ParamConsts.brandOrder,defaultValue = "1")Integer brandOrder) {

        return goodsManageService.doUpdateBrandWithData(request,cateIds,brandId,brandName,brandOrder);
    }

    /**
     * 跳转到添加品牌页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/addBrandPage")
    public String addBrandPage(HttpServletRequest request) {

        return "add_brand";
    }

    /**
     * 添加品牌信息
     * @param request
     * @param cateIds
     * @param brandName
     * @param brandOrder
     * @return
     */
    @RequestMapping(value = "/addBrand")
    @ResponseBody
    public String updateBrand (HttpServletRequest request,
                               @RequestParam(value = ParamConsts.cateIds,defaultValue = "") String cateIds,
                               @RequestParam(value = ParamConsts.brandName,defaultValue = "") String brandName,
                               @RequestParam(value = ParamConsts.brandOrder,defaultValue = "1")Integer brandOrder) {

        return goodsManageService.doAddBrandWithData(request,cateIds,brandName,brandOrder);
    }

    /**
     * 加载 栏目 信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllCate")
    @ResponseBody
    public String getAllCate(HttpServletRequest request) {

        List<CateParentWithSonVo> vos = goodsManageService.getCateParentWithSonVo(request);

        Map<String,Object> map = new HashMap<>(1);

        map.put("vo",vos);

        return JsonUtil.buildData(map);

    }
}
