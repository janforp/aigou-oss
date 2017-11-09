package com.aigou.oss.service;

import com.aigou.oss.api.JuheMessage;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.model.vo.ExpressInfo;
import com.aigou.oss.model.vo.JuheMsgBack;
import com.aigou.oss.util.JsonUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/6/17.
 * 快递信息
 */
@Service
public class ExpressService {

    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;
    @Autowired
    private GoShoplistDAO goShoplistDAO;

    /**
     * 获取快递信息
     *
     * @param userId    用户id
     * @param shopId    商品id
     * @param orderCode 订单号
     * @return
     */
    public ExpressInfo getExpressInfo(Integer userId,Integer shopId,String orderCode) {

        GoMemberGoRecord record = goMemberGoRecordDAO.selectRecordByUserIdAndShopIdAndOrderCode(userId,shopId,orderCode);

        ExpressInfo expressInfo = new ExpressInfo();

        if(record != null) {
            //快递单号
            expressInfo.setCode(record.getCompanyCode());
            /**
             <option <c:if test="${expressInfo.name == '1'}">selected</c:if>value="申通快递">申通快递</option>
             <option <c:if test="${expressInfo.name == '2'}">selected</c:if>value="圆通快递">圆通快递</option>
             <option <c:if test="${expressInfo.name == '3'}">selected</c:if>value="中通快递">中通快递</option>
             <option <c:if test="${expressInfo.name == '4'}">selected</c:if>value="韵达快递">韵达快递</option>
             <option <c:if test="${expressInfo.name == '5'}">selected</c:if>value="天天快递">天天快递</option>
             <option <c:if test="${expressInfo.name == '6'}">selected</c:if>value="顺丰快递">顺丰快递</option>
             */
            //快递公司名
            if("申通快递".equals(record.getCompany())){
                expressInfo.setName(1);
            }
            if("圆通快递".equals(record.getCompany())){
                expressInfo.setName(2);
            }
            if("中通快递".equals(record.getCompany())){
                expressInfo.setName(3);
            }
            if("韵达快递".equals(record.getCompany())){
                expressInfo.setName(4);
            }
            if("天天快递".equals(record.getCompany())){
                expressInfo.setName(5);
            }
            if("顺丰快递".equals(record.getCompany())){
                expressInfo.setName(6);
            }
            if("无需快递".equals(record.getCompany())){
                expressInfo.setName(7);
                expressInfo.setCode("无需快递");
            }

            //快递价格
            expressInfo.setPrice(record.getCompanyMoney());
            //订单状态
            if(record.getStatus().trim().contains("已付款,未发货,未完成")){
                expressInfo.setStatusDesc("未完成");
            }
            if (record.getStatus().trim().contains("已付款,已发货,待收货")){
                expressInfo.setStatusDesc("待收货");
            }
            if (record.getStatus().trim().contains("已付款,已发货,已完成")) {
                expressInfo.setStatusDesc("已完成");
            }
            if(record.getStatus().trim().contains("已付款,未发货,已作废")){
                expressInfo.setStatusDesc("已作废");
            }
            //快递状态
//            expressInfo.setExpressStatus("");
        }
        return expressInfo;

    }

    /**
     * 保存快递信息
     * @param expressName   公司名称代号
     * @param expressCode   快递单号
     * @param expressPrice  快递价格
     * @return
     */
    public String doSaveExpressInfo(Integer expressName,
                                    String expressCode,
                                    BigDecimal expressPrice,
                                    Integer userId,
                                    Integer shopId,
                                    String  orderCode,
                                    String  phone) throws UnsupportedEncodingException {

        GoMemberGoRecord record = goMemberGoRecordDAO.selectRecordByUserIdAndShopIdAndOrderCode(userId,shopId,orderCode);
        /**
         <option <c:if test="${expressInfo.name == '1'}">selected</c:if>value="申通快递">申通快递</option>
         <option <c:if test="${expressInfo.name == '2'}">selected</c:if>value="圆通快递">圆通快递</option>
         <option <c:if test="${expressInfo.name == '3'}">selected</c:if>value="中通快递">中通快递</option>
         <option <c:if test="${expressInfo.name == '4'}">selected</c:if>value="韵达快递">韵达快递</option>
         <option <c:if test="${expressInfo.name == '5'}">selected</c:if>value="天天快递">天天快递</option>
         <option <c:if test="${expressInfo.name == '6'}">selected</c:if>value="顺丰快递">顺丰快递</option>
         */
        if (record != null) {
            if  (expressName == 1) {
                record.setCompany("申通快递");
            }
            if (expressName == 2) {
                record.setCompany("圆通快递");
            }
            if (expressName == 3) {
                record.setCompany("中通快递");
            }
            if (expressName == 4) {
                record.setCompany("韵达快递");
            }
            if (expressName == 5) {
                record.setCompany("天天快递");
            }
            if (expressName == 6) {
                record.setCompany("顺丰快递");
            }
            record.setCompanyCode(expressCode);
            if (expressName == 7) {
                record.setCompany("无需快递");
                record.setCompanyCode("无需快递");
            }
            record.setCompanyMoney(expressPrice);

            /**
             * 填写发货信息,保存成功之后,还要修改
             * status = '已付款,已发货,待收货'
             */
            record.setStatus("已付款,已发货,待收货");

            goMemberGoRecordDAO.updateByPrimaryKeySelective(record);

            //此时,即可给用户发送短信提醒
            //充值成功之后要给用户发送一条短信
            String shopName = goShoplistDAO.selectByPrimaryKey(shopId).getTitle();
            String msgBackStr = JuheMessage.sendMsg(shopName,phone, ValueConsts.MSG_TPL_GOODS_ID);
            JuheMsgBack msgBack = JSON.parseObject(msgBackStr,JuheMsgBack.class);
            //发送短信后返回的描述
            System.out.println(">>>>>>>>>"+msgBack.getReason());

            return JsonUtil.buildSuccess();
        }

        return null;
    }


}
