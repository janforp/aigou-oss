package com.aigou.oss.web.controller.page.console.auth;


import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.model.vo.*;
import com.aigou.oss.service.ExpressService;
import com.aigou.oss.service.LotterySendService;
import com.aigou.oss.service.ShopOrderService;
import com.aigou.oss.service.UserService;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import com.aigou.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/6/14.
 *
 * 中奖发货管理
 */
@RequestMapping(value = "/page/console/auth/lottery",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
@Controller
public class LotterySendController {


    @Autowired
    private LotterySendService lotterySendService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopOrderService shopOrderService;
    @Autowired
    private ExpressService expressService;

    /**
     * 跳转至中奖发货管理页面
     * @return
     */
    @RequestMapping(value = "/lotterylist")
    public String list(){
        return "lottery_list";
    }

    /**
     * 中奖发货管理
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
    @RequestMapping(value = "/getData")
    @ResponseBody
    public String getData(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.pageNum, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = ParamConsts.startTime, defaultValue = "") String startTime,
                          @RequestParam(value = ParamConsts.endTime, defaultValue = "") String endTime,
                          @RequestParam(value = ParamConsts.rank, defaultValue = "") String rank,
                          @RequestParam(value = ParamConsts.paramKey, defaultValue = "") String paramKey,
                          @RequestParam(value = ParamConsts.paramValue, defaultValue = "") String paramValue,
                          @RequestParam(value = ParamConsts.status, defaultValue = "1") String status) throws Exception {

        if(!StringUtil.isEmpty(startTime)) {
            startTime = ElBase.get10PointTimestamp(startTime);
        }else {
            startTime = null;
        }

        if(!StringUtil.isEmpty(endTime)) {
            endTime = ElBase.get10PointTimestamp(endTime);
        }else {
            endTime = null;
        }

        if(StringUtil.isEmpty(paramKey)) {
            paramKey = null;
        }

        if(StringUtil.isEmpty(paramValue)) {
            paramValue = null;
        }

        List<LotteryOrderVo> list = lotterySendService.getLotteryOrder(request,pageNum,startTime,endTime,rank,paramKey,paramValue,status);


        //考虑分页
        Integer total = lotterySendService.getLotteryOrderNums(startTime,endTime,status,paramValue,paramKey);
        Integer totalPage = 1;
        if(total % ValueConsts.PAGE_SIZE == 0){
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE + 1;
        }

        Map<String ,Object> map = new HashMap<>(2);
        map.put("totalPage", totalPage);

        map.put("list", list);

        return JsonUtil.buildData(map);
    }

    /**
     * 验证是否已发过货

     * @param userId        用户id
     * @param shopId        商品id
     * @param orderCode     订单号
     * @return
     */
    @RequestMapping(value = "/checksendornot")
    @ResponseBody
    public String checkSendOrNot(@RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                                 @RequestParam(value = ParamConsts.shopId,required = true) Integer shopId,
                                 @RequestParam(value = ParamConsts.orderCode,required = true)String orderCode){


        return lotterySendService.doCheckSendOrNot(userId,shopId,orderCode);

    }

    /**
     * 跳转到发货页面
     * @param userId
     * @param shopId    商品id
     * @return
     */
    @RequestMapping(value = "/send_page")
    public String toSendItem(HttpServletRequest request,
                             @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                             @RequestParam(value = ParamConsts.shopId,required = true) Integer shopId,
                             @RequestParam(value = ParamConsts.orderCode,required = true)String orderCode,
                             @RequestParam(value = ParamConsts.virtual,required = true) Integer virtual){

        LotteryUserInfo user = userService.selectLotteryUserInfo(userId);
        ExpressInfo expressInfo = expressService.getExpressInfo(userId,shopId,orderCode);

        request.setAttribute("user",user);
        request.setAttribute("shopId",shopId);
        request.setAttribute("orderCode",orderCode);
        request.setAttribute("virtual",virtual);
        request.setAttribute("expressInfo",expressInfo);
        return "send_page";
    }



    /**
     * 商品,订单详细信息
     * @param request
     * @param userId
     * @param shopId
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "/getdetailInfo")
    public String showOrderDetail(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                                  @RequestParam(value = ParamConsts.shopId,required = true) Integer shopId,
                                  @RequestParam(value = ParamConsts.orderCode,required = true)String orderCode) {

        ShopOrderInfo info = shopOrderService.getShopOrderInfo(userId,shopId,orderCode);
        ExpressInfo expressInfo = expressService.getExpressInfo(userId,shopId,orderCode);
        //商品及中奖人信息
        request.setAttribute("info",info);
        //快递信息
        request.setAttribute("expressInfo",expressInfo);
        //商品ID,因为在更新快递状态的时候需要用到
        request.setAttribute("shopId",shopId);
        //同上
        request.setAttribute("orderCode",orderCode);
        //同上
        return "order_detail";
    }

    /**
     * 更新订单状态(待收货-->已完成,或 待收货-->已作废)
     * @param request
     * @param statusDesc   更新之后的订单状态
     * @param userId
     * @param shopId
     * @param orderCode     订单号
     * @param lotteryCode   中奖码
     * @return
     */
    @RequestMapping(value = "/updateExpressInfo")
    @ResponseBody
    public String updataExpressInfo(HttpServletRequest request,
                                    @RequestParam(value = ParamConsts.statusDesc,required = true) Integer statusDesc,
                                    @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                                    @RequestParam(value = ParamConsts.shopId,required = true) Integer shopId,
                                    @RequestParam(value = ParamConsts.orderCode,required = true)String orderCode,
                                    @RequestParam(value = ParamConsts.lotteryCode,required = true)String lotteryCode) {

        /**
         <option <c:if test="${expressInfo.statusDesc == '未完成'}">selected</c:if> value="1">未完成</option>
         <option <c:if test="${expressInfo.statusDesc == '待收货'}">selected</c:if> value="2">待收货</option>
         <option <c:if test="${expressInfo.statusDesc == '已完成'}">selected</c:if> value="3">已完成</option>
         <option <c:if test="${expressInfo.statusDesc == '已作废'}">selected</c:if> value="4">已作废</option>
         */

        return lotterySendService.doUpdateExpressInfo(request,statusDesc,userId,shopId,orderCode,lotteryCode);
    }
}
