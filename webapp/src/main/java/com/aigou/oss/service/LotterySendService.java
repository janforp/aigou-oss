package com.aigou.oss.service;

import com.aigou.oss.consts.BaseConsts;
import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoMember;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.model.vo.LotteryOrderVo;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/6/16.
 * 中奖发货管理
 */
@Service
public class LotterySendService {

    @Autowired
    private GoShoplistDAO goShoplistDAO;
    @Autowired
    private GoMemberDAO goMemberDAO;
    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;



    public Integer getLotteryOrderNums(String startTime,String endTime,String status,String paramValue,String paramKey){
        return goShoplistDAO.selectLotteryOrderNum(startTime,endTime,status,paramValue,paramKey);
    }


    /**
     * 中奖发货管理(列表显示)
     * @param request
     * @param pageNum       页码
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param rank          按时间顺序开奖规则
     * @param paramKey      查询条件(uid,phone,name,email)
     * @param paramValue    查询条件值
     * @param status        订单状态(未发货1,已发货2,已完成3,全部0)
     * @return
     */
    public List<LotteryOrderVo> getLotteryOrder(HttpServletRequest request,
                                                Integer pageNum,
                                                String startTime,
                                                String endTime,
                                                String rank,
                                                String paramKey,
                                                String paramValue,
                                                String status){

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);
        List<GoShoplist> list = goShoplistDAO.selectLotteryOrder(startTime,endTime,status,paramKey,paramValue,rank,bounds);

        List<LotteryOrderVo> orderList = new ArrayList<>();

        //组装时需要根据cataid查询此商品是否是虚拟商品
        if(list != null && list.size() != 0){
            for(GoShoplist shop : list){
                LotteryOrderVo vo = new LotteryOrderVo();

                Integer shopid = shop.getId();
                vo.setShopId(shopid);
                vo.setQishu(shop.getQishu());
                vo.setTitle(shop.getTitle());
                String time = shop.getQEndTime().replace(".","");
                time = ElBase.fmtTime(Long.parseLong(time));
                //开奖时间
                vo.setQ_end_time(time);
                //中奖号码
                String huode = shop.getQUserCode();
                vo.setQ_user_code(huode);
                Integer uid = shop.getQUid();
                vo.setUserId(uid);
                //中奖用户昵称
                GoMember member = goMemberDAO.selectByPrimaryKey(uid);
                if(member != null) {
                    vo.setNickName(member.getUsername());
                }
                //订单号
                GoMemberGoRecord goMemberGoRecord = goMemberGoRecordDAO.selectRecordByShopIdAndHuode(shopid,huode);
                String orderCode = goMemberGoRecord.getCode();
                vo.setOrderCode(orderCode);
                /**
                 是否是虚拟商品(虚拟商品:0,非虚拟商品:1)
                 private Integer virtualOrNot;
                 */
                Integer virtualShopId = goShoplistDAO.selectVirtualShopId(uid,orderCode);
                if (virtualShopId != null){
                    //是虚拟商品
                    vo.setVirtualOrNot(0);
                }
                if (virtualShopId == null) {
                    //非虚拟商品
                    vo.setVirtualOrNot(1);
                }
                //购买次数(单次的,不是此用户多次购买此商品的总次数)
                Integer buyNums = goMemberGoRecord.getGonumber();
                vo.setBuyNums(buyNums);
                //订单状态
                String statusDesc = goMemberGoRecord.getStatus();

                if(statusDesc != null ) {

                    if(statusDesc.trim().equals("已付款,未发货,未完成")){
                        vo.setStatusDesc("未完成");
                    }
                    if (statusDesc.trim().equals("已付款,已发货,待收货")){
                        vo.setStatusDesc("待收货");
                    }
                    if (statusDesc.trim().equals("已付款,已发货,已完成")) {
                        vo.setStatusDesc("已完成");
                    }
                    if(statusDesc.trim().contains("已作废")){
                        vo.setStatusDesc("已作废");
                    }
                }

                orderList.add(vo);
            }
        }
        return orderList;
    }


    /**
     * 验证是否已发货
     *
     * @param userId
     * @param shopId
     * @param orderCode     订单号
     * @return
     */
    public String doCheckSendOrNot(Integer userId,Integer shopId,String orderCode){

        GoMemberGoRecord record = goMemberGoRecordDAO.selectRecordByUserIdAndShopIdAndOrderCode(userId,shopId,orderCode);

        if(record != null) {

            if(record.getStatus().contains("已发货")) {
                return JsonUtil.buildError(BaseConsts.ALREADY_SEND);
            }
            if(record.getStatus().contains("已作废")) {
                return JsonUtil.buildError(BaseConsts.CANCEL_ORDER);
            }
            return JsonUtil.buildSuccess();
        }

        return null;

    }

    /**
     * 更新订单状态(待收货-->已完成,或 待收货-->已作废)
     * @param request
     * @param orderStatus   更新之后的订单状态
     * @param userId
     * @param shopId
     * @param orderCode     订单号
     * @param lotteryCode   中奖码
     * @return
     */
    public String doUpdateExpressInfo(HttpServletRequest request,
                                      Integer  orderStatus,
                                      Integer userId,
                                      Integer shopId,
                                      String orderCode,
                                      String lotteryCode) {

        /**
         <option <c:if test="${expressInfo.statusDesc == '未完成'}">selected</c:if> value="1">未完成</option>
         <option <c:if test="${expressInfo.statusDesc == '待收货'}">selected</c:if> value="2">待收货</option>
         <option <c:if test="${expressInfo.statusDesc == '已完成'}">selected</c:if> value="3">已完成</option>
         <option <c:if test="${expressInfo.statusDesc == '已作废'}">selected</c:if> value="4">已作废</option>
         */
        //更新表
        int result = goMemberGoRecordDAO.updateOrderStatusByOrderCodeUserIdShopIdOrderCodelotteryCode
                                            (orderStatus,userId,shopId,orderCode,lotteryCode);

        if(result != 0) {
            return JsonUtil.buildSuccess();
        }
        return null;
    }
}
