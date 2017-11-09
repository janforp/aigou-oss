package com.aigou.oss.service;

import com.aigou.oss.dao.*;
import com.aigou.oss.model.*;
import com.aigou.oss.model.vo.ShopCode;
import com.aigou.oss.model.vo.UserCodeVo;
import com.aigou.oss.util.CommonMethod;
import com.aigou.oss.util.RandomUtil;
import com.aigou.oss.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Summer on 16/6/21.
 */
@Service
public class PurchaseService {

    @Autowired
    private AgInnerAccountDAO agInnerAccountDAO;

    @Autowired
    private AgParamConfigDAO agParamConfigDAO;

    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;

    @Autowired
    private AgRandomTimesDAO agRandomTimesDAO;

    @Autowired
    private GoShoplistDAO goShoplistDAO;

    @Autowired
    private GoMemberDAO goMemberDAO;

    @Autowired
    private GoShopcodes1DAO goShopcodes1DAO;

    @Autowired
    private GoodsManageService goodsManageService;

    public void Purchase(GoShoplist shop) throws Exception {

//        int beginId = shop.getQuyuBegin();
//        int endId = shop.getQuyuEnd();
//        int shopId = shop.getId();
//
//        if(beginId > 0 && endId > 0) { // 设定了中奖区间
//
//            // 判断区间内用户是否购买过
//            GoMemberGoRecord innerRecord = goMemberGoRecordDAO.selectLotteryRecord(shopId, shop.getQishu(), beginId, endId);
//
//            if(innerRecord == null) { // 区间内用户未购买
//
//                // 判断是否有用户购买过
//                GoMemberGoRecord record = goMemberGoRecordDAO.selectRecordByShopId(shopId);
//
//                if(record == null) { // 没有用户购买过
//
//                    // 随机1-5个用户
//                    int userNum = RandomUtil.getRandomBetweenMaxAndMin(5, 1);
//
//                    // 查询用户
//                    List<Integer> userList = agInnerAccountDAO.selectUserIdByLimit(userNum);
//                    for (Integer userId : userList) {
//                        // 购买1次商品
//                        purchaseByUserIdAndNum(userId, 1, shopId);
//                    }
//
//                }
//
//                // 查询一个区间用户去购买
//                regionUserPurchase(beginId, endId, shop);
//
//
//            } else { // 区间内用户已购买
//
//                purchaseByRandom(shop);
//
//            }
//
//
//        } else {  // 未设定了中奖区间
//
//            purchaseByRandom(shop);
//
//        }

        if(shop != null) {
            purchaseByRandom(shop);
        }

    }

    /**
     * 查询一个区间用户去购买
     * @param beginId
     * @param endId
     * @param shop
     */
    private void regionUserPurchase(Integer beginId, Integer endId, GoShoplist shop) throws Exception {

        // 查询一个区间用户去购买
        Integer userId = agInnerAccountDAO.selectUserIdByRegion(beginId, endId);

        // 查询购买次数
        int buyNum = getBuyNum(shop.getYunjiage(), shop.getZongrenshu());

        // 购买商品
        purchaseByUserIdAndNum(userId, buyNum, shop.getId());

    }

    /**
     * 随机购买
     * @param shop
     */
    private void purchaseByRandom(GoShoplist shop) throws Exception {
        // 查询随机用户个数
        AgParamConfig userConfig = agParamConfigDAO.selectByPrimaryKey("RANDOM_USER");
        int userNum = RandomUtil.getRandomBetweenMinAndMax(Integer.valueOf(userConfig.getFirstValue()), Integer.valueOf(userConfig.getSecondValue()));

        // 查询未购买过的内部用户
        List<Integer> userList = agInnerAccountDAO.selectUnPurchase(shop.getId(), userNum);

        if(userList != null) {
            for (Integer userId : userList) {

//                int second = RandomUtil.getRandomBetweenMinAndMax(1, 3);
//                Thread.sleep(second * 1000);

                int buyNum = getBuyNum(shop.getYunjiage(), shop.getZongrenshu());

                // 购买商品
                purchaseByUserIdAndNum(userId, buyNum, shop.getId());

            }
        }
    }

    /**
     * 购买数量
     * @param money
     * @return
     */
    private int getBuyNum(BigDecimal money, int total) {
        // 查询购买次数
        int buyNum = agRandomTimesDAO.selectByRandom();

        if(money.intValue() == 1) {
            if(total < 100) {
                buyNum = CommonMethod.getMinValue(5, buyNum);
            }else if (total < 500) {
                buyNum = CommonMethod.getMinValue(20, buyNum);
            }else if (total < 1000) {
                buyNum = CommonMethod.getMinValue(50, buyNum);
            }
        }else if(money.intValue() == 10) {
            buyNum = CommonMethod.getMaxValue(1, buyNum / 10);
            buyNum = buyNum * 10;
        }else if(money.intValue() == 100) {
            buyNum = CommonMethod.getMaxValue(1, buyNum / 100);
            buyNum = buyNum * 100;
        }

        return buyNum;
    }


    /**
     * 购买商品
     * @param userId
     * @param buyNum
     * @param shopId
     */
    private void purchaseByUserIdAndNum(Integer userId, int buyNum, Integer shopId) throws Exception {

        // 查询商品剩余
        GoShoplist shop = goShoplistDAO.selectByPrimaryKey(shopId);
        int left = shop.getShenyurenshu();

        if(left > 0) { // 还有剩余

            // 判断实际能购买的次数
            if(buyNum > left) {
                buyNum = left;
            }

            // 生成订单号
            String orderCode = getOrderCode("A");

            // 下单购买
            GoMemberGoRecord record = getRecord(orderCode, userId, buyNum, shop);

            if(record != null) {
                // 更新商品
                GoShoplist item = new GoShoplist();
                item.setId(shop.getId());
                // 云购码数量
                int goNum = record.getGonumber();
                if(goNum >= left) {
                    item.setShenyurenshu(0);
                    item.setCanyurenshu(shop.getZongrenshu());
                }else {
                    item.setCanyurenshu(shop.getCanyurenshu() + goNum);
                    item.setShenyurenshu(shop.getShenyurenshu() - goNum);
                }
                goShoplistDAO.updateByPrimaryKeySelective(item);

            }

        }

    }

    /**
     * 生成订单号
     *
     * @param head 订单头
     * @return
     */
    public String getOrderCode(String head) {

        return head + System.currentTimeMillis() + RandomUtil.getRandomString(6);

    }

    /**
     * 下单
     * @param orderCode
     * @param userId
     * @param buyNum
     * @param shop
     * @return
     * @throws Exception
     */
    public GoMemberGoRecord getRecord(String orderCode, Integer userId, int buyNum, GoShoplist shop) throws Exception {

        GoMemberGoRecord record = null;

        GoMember user = goMemberDAO.selectByPrimaryKey(userId);
        String photo = user.getImg();
        String userName = getUserName(user);
        int orderCodeTmp = 0;
        String ip = user.getUserIp();

        UserCodeVo userCode = getUserCode(shop, buyNum);

        int num = userCode.getNum();

        if(num > 0) {

            record = new GoMemberGoRecord();

            record.setCode(orderCode);
            record.setCodeTmp(orderCodeTmp);
            record.setUsername(userName);
            record.setUphoto(photo);
            record.setUid(userId);
            record.setShopid(shop.getId());
            record.setShopname(shop.getTitle());
            record.setShopqishu(shop.getQishu());
            String codes = userCode.getCode();
            record.setGoucode(codes);
            record.setGonumber(num);
            record.setIp(ip);
            record.setTime(String.format("%.3f", (double)(System.currentTimeMillis()/1000.0)));
            record.setHuode("0");
            record.setCompanyMoney(new BigDecimal(0));
            BigDecimal money = new BigDecimal(num);
            record.setMoneycount(money);
            record.setPayType("账户");
            record.setStatus("已付款,未发货,未完成");

            goMemberGoRecordDAO.insertSelective(record);

            // 剩余码的个数
            int sum = goShopcodes1DAO.selectSumSlen(shop.getId());
            if(sum == 0) {

                int qishu = shop.getQishu();
                int maxqishu = shop.getMaxqishu();

                // 新建商品
                if(qishu < maxqishu) {

                    // 当前最新一期
                    GoShoplist maxShop = goShoplistDAO.selectLastShop(shop.getSid());

                    int max = maxShop.getQishu();

                    if(max <= qishu) {
                        content_add_shop_install(maxShop);
                    }

                }

            }

        }else {// 商品码已经卖完

            // 判断商品剩余量
            GoShoplist item = goShoplistDAO.selectByPrimaryKey(shop.getId());

            if(item.getShenyurenshu() > 0) {

                GoShoplist shoplist = new GoShoplist();
                shoplist.setShenyurenshu(0);
                shoplist.setCanyurenshu(item.getZongrenshu());
                goShoplistDAO.updateByPrimaryKeySelective(shoplist);

            }


        }

        return record;

    }

    /**
     * 获取用户名
     * @param member
     * @return
     */
    private String getUserName(GoMember member) {

        String name = member.getUsername();

        if(StringUtil.isEmpty(name)){

            name = member.getMobile();

            if(StringUtil.isEmpty(name)) {

                name = member.getEmail();

                if(StringUtil.isEmpty(name)) {

                    name = "手机用户" + String.valueOf(member.getUid() + 1462395905);

                }else {

                    String[] mail = name.split("@");

                    int len = mail[0].length() / 2;

                    if(len == 0) {
                        name = mail[0] + "***@" + mail[1];
                    }else {
                        name = mail[0].substring(0, len) + "***@" + mail[1];
                    }

                }

            }else {

                name = name.substring(0, 3) + "****" + name.substring(7);

            }

        }

        return name;

    }


    /**
     * 生成购买的云购码
     *
     * @param shop 商品
     * @param userNum 生成个数
     * @return
     */
    public UserCodeVo getUserCode(GoShoplist shop, int userNum) throws Exception {

        UserCodeVo userCode = new UserCodeVo();

        String user_code = "";
        int user_code_len = 0;//云够码长度

        List<GoShopcodes1> list = new ArrayList<>();

        // 商品id
        Integer sid = shop.getId();

        // 获取云购码
        GoShopcodes1 shopcodes = goShopcodes1DAO.selectShopcodesBySid(sid);

        list.add(shopcodes);

        // 剩余个数
        int codes_count_len = shopcodes.getSLen();

        int scid = shopcodes.getSCid();

        if(codes_count_len < userNum && scid > 1) {

            for(int i = scid-1; i >= 1; i--) {

                GoShopcodes1 shopcode = goShopcodes1DAO.selectShopcodesBySidAndScid(sid, i);

                list.add(shopcode);

                codes_count_len += shopcode.getSLen();

                if(codes_count_len > userNum) {
                    break;
                }

            }


        }

        if(codes_count_len < userNum) {
            userNum = codes_count_len;
        }

        for (GoShopcodes1 code : list) {

            int codeNum = userNum;

            if(codeNum > 0) {

                List<Integer> codeList = ShopCode.getUnserializeList(code.getSCodes());

                if(codeList.size() < codeNum) {
                    codeNum = codeList.size();
                }

                // 打乱顺序
                Collections.shuffle(codeList);

                List<Integer> codeTmpList = codeList.subList(0, codeNum);

                for (Integer codeTmp : codeTmpList) {

                    if(user_code != "") {
                        user_code += "," + codeTmp;
                    }else {
                        user_code = codeTmp + "";
                    }

                }

                int codeTmpLen = codeTmpList.size();

                // 剩余的云购码
                List<Integer> leftCodeList = new ArrayList<>();
                leftCodeList.addAll(codeList.subList(codeNum, codeList.size()));

                int leftCodeNum = 0;
                String leftCode = "a:0:{}";

                if(leftCodeList != null && leftCodeList.size() > 0) {
                    leftCodeNum = leftCodeList.size();
                    leftCode = ShopCode.getSerialize(leftCodeList);
                }

                GoShopcodes1 record = new GoShopcodes1();
                record.setId(code.getId());
                record.setSCodes(leftCode);
                record.setSLen(leftCodeNum);

                if(leftCodeNum == 0) {
                    record.setSCid(0);
                }

                goShopcodes1DAO.updateByPrimaryKeySelective(record);

                user_code_len += codeTmpLen;
                userNum -= codeTmpLen;

            }

        }

        userCode.setCode(user_code);
        userCode.setNum(user_code_len);

        return userCode;

    }

    /**
     * 新建一期商品
     *
     * @param shop 商品
     * @return
     */
    public void content_add_shop_install(GoShoplist shop) throws Exception {

        GoShoplist item = new GoShoplist();

        item.setSid(shop.getSid());
        item.setCateid(shop.getCateid());
        item.setBrandid(shop.getBrandid());
        item.setTitle(shop.getTitle());
        item.setTitleStyle(shop.getTitleStyle());
        item.setTitle2(shop.getTitle2());
        item.setKeywords(shop.getKeywords());
        item.setDescription(shop.getDescription());
        item.setMoney(shop.getMoney());
        item.setYunjiage(shop.getYunjiage());
        item.setZongrenshu(shop.getZongrenshu());
        item.setCanyurenshu(0);
        item.setShenyurenshu(shop.getZongrenshu());
        item.setDefRenshu(shop.getDefRenshu());
        item.setQishu(shop.getQishu()+1);
        item.setMaxqishu(shop.getMaxqishu());
        item.setThumb(shop.getThumb());
        item.setPicarr(shop.getPicarr());
        item.setContent(shop.getContent());
        item.setCodesTable(shop.getCodesTable());
        item.setXsjxTime(0);
        item.setPos(shop.getPos());
        item.setRenqi(shop.getRenqi());
        item.setTime((int) (System.currentTimeMillis()/1000));
        item.setOrder(shop.getOrder());
        item.setQShowtime("N");
        item.setRenqipos(shop.getRenqipos());
        item.setNewpos(shop.getNewpos());
        item.setBannershop(shop.getBannershop());
        item.setPosthumb(shop.getPosthumb());
        item.setQuyu(shop.getQuyu());
        item.setQuyuBegin(shop.getQuyuBegin());
        item.setQuyuEnd(shop.getQuyuEnd());

        goShoplistDAO.insertSelective(item);

        // 生成云购码
        goodsManageService.createCodesByZonRenShu(item);
//
//        int countNum = item.getZongrenshu();
//        int len = 3000;
//
//        int num = countNum / len + 1;
//        int code_i = countNum;
//
//        if(num == 1) {
//
//            List<Integer> list = new ArrayList<>();
//
//            for (int i=1; i<=countNum; i++) {
//                list.add(10000000 + i);
//            }
//
//            String code = ShopCode.getSerialize(list);
//
//            GoShopcodes1 codes = new GoShopcodes1();
//            codes.setSId(item.getId());
//            codes.setSCid(1);
//            codes.setSLen(countNum);
//            codes.setSCodes(code);
//            codes.setSCodesTmp(code);
//
//            goShopcodes1DAO.insert(codes);
//
//        }else {
//
//            for(int i=1; i<num; i++) {
//
//                List<Integer> list = new ArrayList<>();
//
//                for (int j=1; j<=len; j++) {
//
//                    list.add(10000000+code_i);
//                    code_i--;
//
//                }
//
//                String code = ShopCode.getSerialize(list);
//
//                GoShopcodes1 codes = new GoShopcodes1();
//                codes.setSId(item.getId());
//                codes.setSCid(i);
//                codes.setSLen(countNum);
//                codes.setSCodes(code);
//                codes.setSCodesTmp(code);
//
//                goShopcodes1DAO.insert(codes);
//
//            }
//
//            countNum = countNum - ((num-1)*len);
//
//            List<Integer> list = new ArrayList<>();
//
//            for (int i=1; i<=countNum; i++) {
//                list.add(10000000 + code_i);
//                code_i--;
//            }
//
//            String code = ShopCode.getSerialize(list);
//
//            GoShopcodes1 codes = new GoShopcodes1();
//            codes.setSId(item.getId());
//            codes.setSCid(num);
//            codes.setSLen(countNum);
//            codes.setSCodes(code);
//            codes.setSCodesTmp(code);
//
//            goShopcodes1DAO.insert(codes);
//
//        }

    }

}
