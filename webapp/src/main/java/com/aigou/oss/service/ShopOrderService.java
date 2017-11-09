package com.aigou.oss.service;

import com.aigou.oss.dao.GoMemberDizhiDAO;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoMemberDizhi;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.model.vo.ShopOrderInfo;
import com.aigou.oss.model.vo.UserInfo;
import com.aigou.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jan on 16/6/17.
 * 商品,订单详细信息
 */
@Service
public class ShopOrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private GoShoplistDAO goShoplistDAO;
    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;
    @Autowired
    private GoMemberDizhiDAO goMemberDizhiDAO;


    /**
     * 订单详情页
     *
     * @param userId    用户id
     * @param shopId    商品id
     * @param orderCode 订单号
     * @return
     */
    public ShopOrderInfo getShopOrderInfo(Integer userId,Integer shopId,String orderCode) {

        ShopOrderInfo info = new ShopOrderInfo();

        UserInfo user = userService.selectUser(userId);
        info.setUserName(user.getUserName());
        info.setPhone(user.getPhone());

        //显示在收货地址栏上的手机,最好是地址表中的手机
        GoMemberDizhi dizhi = goMemberDizhiDAO.selectAddressByUidAndDefault(userId);
        if(dizhi != null) {

            String phone = dizhi.getMobile();
            if(phone == null) {
                phone = "";
            }

            String shouhuoren = dizhi.getShouhuoren();
            if(shouhuoren == null ){
                shouhuoren = "";
            }

            String sheng  = dizhi.getSheng();
            if(sheng == null) {
                sheng = "";
            }
            String shi = dizhi.getShi();
            if(shi == null) {
                shi = "";
            }
            String xian = dizhi.getXian();
            if(xian == null){
                xian = "";
            }
            String jiedao = dizhi.getJiedao();
            if(jiedao == null) {
                jiedao = "";
            }

            info.setDizhi("收货人姓名: "+shouhuoren+",详细地址: "+sheng+shi+xian+jiedao+",手机号: "+phone);
        }
        if(dizhi == null ) {

            List<GoMemberDizhi> dizhis = goMemberDizhiDAO.selectAddressesByUid(userId);
            if(dizhis == null || dizhis.size() ==0) {
                info.setDizhi("该用户没有默认地址,也没有填写非默认地址");
            }else {
                //取出第一条地址作为发货地址
                GoMemberDizhi  firstDizhi = dizhis.get(0);

                String phone = firstDizhi.getMobile();
                if(phone == null) {
                    phone = "";
                }

                String shouhuoren = firstDizhi.getShouhuoren();
                if(shouhuoren == null ){
                    shouhuoren = "";
                }

                String sheng  = firstDizhi.getSheng();
                if(sheng == null) {
                    sheng = "";
                }
                String shi = firstDizhi.getShi();
                if(shi == null) {
                    shi = "";
                }
                String xian = firstDizhi.getXian();
                if(xian == null){
                    xian = "";
                }
                String jiedao = firstDizhi.getJiedao();
                if(jiedao == null) {
                    jiedao = "";
                }

                info.setDizhi("收货人姓名: "+shouhuoren+",详细地址: "+sheng+shi+xian+jiedao+",手机号: "+phone);
            }

        }


        GoShoplist shop = goShoplistDAO.selectByPrimaryKey(shopId);
        info.setTitle(shop.getTitle());
        info.setShengyuNums(shop.getShenyurenshu());
        info.setTotalNums(shop.getZongrenshu());
        info.setQishu(shop.getQishu());
        info.setPrice(shop.getMoney());
        info.setUserId(userId);
        info.setEmail(user.getEmail());

        GoMemberGoRecord record = goMemberGoRecordDAO.selectRecordByUserIdAndShopIdAndOrderCode(userId,shopId,orderCode);
        //中奖云购码
        if(record != null) {
            String lotteryCode = record.getHuode();
            info.setLotteryCode(lotteryCode);
            //购买次数
            info.setBuyNums(record.getGonumber());
            //该用户所有的云够码
            String gouCode = record.getGoucode();
            String[] codes = gouCode.split(",");
            info.setTotalBuyCodes(codes);
            //购买时间
            String time = record.getTime();
            time = time.replace(".","");
            time = ElBase.fmtTime(Long.parseLong(time));
            info.setBuyTime(time);
        }
        return info;

    }
}
