package com.aigou.oss.service;

import com.aigou.oss.api.JuheMessage;
import com.aigou.oss.api.JuheMobile;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.vo.JuheBack;
import com.aigou.oss.model.vo.JuheMsgBack;
import com.aigou.oss.util.JsonUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Summer on 16/6/27.
 */
@Service
public class JuheService {


    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;
    @Autowired
    private GoShoplistDAO goShoplistDAO;


    /**
     * 虚拟商品 : 话费充值
     * @param request
     * @param phone         手机号码
     * @param money         充值金额
     * @param orderCode     订单号(一个订单号,只能充值一次)
     * @param userId        用户ID
     * @param shopId        商品ID
     * @return
     * @throws Exception
     */
    public String telephoneRecharge(HttpServletRequest request,
                                    String phone, int money,
                                    String orderCode,
                                    Integer userId,
                                    Integer shopId) throws Exception {

        //先从数据库中盘点此订单号是否已经完成
        GoMemberGoRecord goMemberGoRecord = goMemberGoRecordDAO.selectRecordByUserIdAndShopIdAndOrderCode(userId,shopId,orderCode);
        String status = goMemberGoRecord.getStatus();
        if ("已付款,已发货,已完成".equals(status)) {
            return JsonUtil.buildError("该订单已发货");
        }
        //先判断此手机能否充值
        int error_code = JuheMobile.telCheck(phone,money);
        JuheBack back = null;
        if (error_code == 0){
            //若能充值,则立刻充值
            String result = JuheMobile.onlineOrder(phone, money, orderCode);
            back = JSON.parseObject(result, JuheBack.class);

            if (back.getError_code() == 0 || back.getError_code() ==100014) {

                //修改订单状态
                goMemberGoRecordDAO.updateOrderCodeByUserIdShopIdOrderCode(userId,shopId,orderCode);
                //充值成功之后要给用户发送一条短信
                String shopName = goShoplistDAO.selectByPrimaryKey(shopId).getTitle();
                String msgBackStr = JuheMessage.sendMsg(shopName,phone, ValueConsts.MSG_TPL_VIRTUAL_ID);
                JuheMsgBack msgBack = JSON.parseObject(msgBackStr,JuheMsgBack.class);
                //发送短信后返回的描述
                System.out.println(">>>>>>>>>"+msgBack.getReason());
                //返回充值成功之后的reason
                return JsonUtil.buildSuccess(back.getReason());
            }
        }
        //返回充值失败的原因
        return JsonUtil.buildError(back.getReason());
    }

}
