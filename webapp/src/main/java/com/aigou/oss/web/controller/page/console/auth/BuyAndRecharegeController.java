package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.model.vo.ShowAccountVo;
import com.aigou.oss.model.vo.ShowRecordVo;
import com.aigou.oss.service.BuyAndRecharegeService;
import com.aigou.oss.service.JuheService;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import com.aigou.oss.util.el.ElBase;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/6/23.
 *
 * 查找模块
 */
@Controller
@RequestMapping(value = "/page/console/auth/buyAndRecharge",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class BuyAndRecharegeController {


    @Autowired
    private BuyAndRecharegeService buyAndRecharegeService;
    @Autowired
    private JuheService juheService;

    /**
     * 跳转到  查询用户 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/findUser")
    public String findUser (HttpServletRequest request) {

        return "find_user";

    }

    /**
     * 点击用户查询 按钮
     * @param request
     * @param paramKey      查询条件
     * @param paramValue    输入值
     * @param pageNum       页码
     * @return
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    public String userList (HttpServletRequest request,
                            @RequestParam(value = ParamConsts.paramKey,required = true) String paramKey,
                            @RequestParam(value = ParamConsts.paramValue,required = true) String paramValue,
                            @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {

        return buyAndRecharegeService.listUsers(request,paramKey,paramValue,pageNum);

    }

    /**
     * 查询用户的   购买纪录 分页
     *
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/showRecords")
    public String showRecords (HttpServletRequest request,
                               @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                               @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {

        List<ShowRecordVo> records = buyAndRecharegeService.getShowRecords(request,userId,pageNum);
        int total = buyAndRecharegeService.getShowTotalRecords(request,userId);
        int totalPage = 1;
        if(total % ValueConsts.PAGE_SIZE == 0){
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE + 1;
        }
        int pageNow = 1;

        //若此用户没有购买纪录,则在第一页显示 0/0
        if (totalPage == 0) {
            pageNow = 0;
        }


        request.setAttribute("totalPage",totalPage);
        request.setAttribute("records",records);
        request.setAttribute("userId",userId);
        request.setAttribute("pageNow",pageNow);

        return "buy_record";
    }

    /**
     * 上面的是有页面跳转的,即第一页的数据
     *
     * 这个方法是在此页面点击翻页触发的
     *
     * @param request
     * @param userId
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/showRecordByPage")
    @ResponseBody
    public String showRecordByPage (HttpServletRequest request,
                               @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                               @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {

        List<ShowRecordVo> records = buyAndRecharegeService.getShowRecords(request,userId,pageNum);

        Map<String,Object> map = new HashMap<>(1);
        map.put("records",records);

        return JsonUtil.buildData(map);
    }

    /**
     * 弹出显示获得码的页面
     * @param request
     * @param allCodes      获得云够码字符串
     * @param huode
     * @return
     */
    @RequestMapping(value = "/showCodes")
    public String showCodes (HttpServletRequest request,
                             @RequestParam(value = ParamConsts.allCodes,required = true) String allCodes,
                             @RequestParam(value = ParamConsts.lotteryCode,required = true) String huode) {

        String[] codes = allCodes.split(",");
        String str = JSON.toJSONString(codes);
        request.setAttribute("str",str);
        request.setAttribute("codes",codes);
        request.setAttribute("huode",huode);

        return "show_codes";
    }


    /**
     * 查询用户的   充值纪录 分页
     *
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "/showAccounts")
    public String showAccounts (HttpServletRequest request,
                                @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                                @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {


        List<ShowAccountVo>  accounts = buyAndRecharegeService.getShowAccount(request,userId,pageNum);

        int total = buyAndRecharegeService.getShowTotalAccounts(request,userId);
        int totalPage = 1;
        if(total % ValueConsts.PAGE_SIZE == 0){
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE + 1;
        }
        int pageNow = 1;

        //若此用户没有购买纪录,则在第一页显示 0/0
        if (totalPage == 0) {
            pageNow = 0;
        }


        request.setAttribute("totalPage",totalPage);
        request.setAttribute("accounts",accounts);
        request.setAttribute("userId",userId);
        request.setAttribute("pageNow",pageNow);

        return "account_record";
    }


    /**
     * 上面的是有页面跳转的,即第一页的数据
     *
     * 这个方法是在此页面点击翻页触发的
     *
     * @param request
     * @param userId
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/showAccountByPage")
    @ResponseBody
    public String showAccountByPage (HttpServletRequest request,
                                     @RequestParam(value = ParamConsts.userId,required = true) Integer userId,
                                     @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) {

        List<ShowAccountVo> accounts = buyAndRecharegeService.getShowAccount(request,userId,pageNum);

        Map<String,Object> map = new HashMap<>(1);
        map.put("accounts",accounts);

        return JsonUtil.buildData(map);
    }


    /**
     * 查找模块---功能二:充值查询
     */

    /**
     * 跳转到充值查询页面
     * @return
     */
    @RequestMapping(value = "/findRecharge")
    public String findRecharge (HttpServletRequest request) {

        return "find_recharge";
    }

    /**
     * 充值查询  分页
     * @param request
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    @RequestMapping(value = "/getRechargeData")
    @ResponseBody
    public String getRechargeData (HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.startTime,defaultValue = "") String startTime,
                                   @RequestParam(value = ParamConsts.endTime,defaultValue = "") String endTime,
                                   @RequestParam(value = ParamConsts.paramKey,defaultValue = "") String paramKey,
                                   @RequestParam(value = ParamConsts.paramValue,defaultValue = "") String paramValue,
                                   @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) throws Exception {


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

        List<ShowAccountVo> accounts = buyAndRecharegeService.doGetRechargeData(request,startTime,endTime,paramKey,paramValue,pageNum);

        Integer total = buyAndRecharegeService.doGetRechargeDataNum(request,startTime,endTime,paramKey,paramValue);
        Integer totalPage = 1;
        if (total % ValueConsts.PAGE_SIZE == 0) {
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE  + 1 ;
        }
        if (accounts== null ||accounts.size() == 0) {
            return JsonUtil.buildError("没有满足要求的纪录");
        }
        Map<String,Object> map = new HashMap<>(2);
        map.put("accounts",accounts);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);

    }

    /**
     * 跳转到购买纪录查询页面
     * @return
     */
    @RequestMapping(value = "/findBuy")
    public String findBuy() {

        return "find_buy";
    }

    /**
     * 购买纪录查询  分页
     * @param request
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    @RequestMapping(value = "/getBuyData")
    @ResponseBody
    public String getBuyData ( HttpServletRequest request,
                               @RequestParam(value = ParamConsts.startTime,defaultValue = "") String startTime,
                               @RequestParam(value = ParamConsts.endTime,defaultValue = "") String endTime,
                               @RequestParam(value = ParamConsts.paramKey,defaultValue = "") String paramKey,
                               @RequestParam(value = ParamConsts.paramValue,defaultValue = "") String paramValue,
                               @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum) throws Exception {


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

        List<ShowRecordVo> records = buyAndRecharegeService.doGetBuyData(request,startTime,endTime,paramKey,paramValue,pageNum);

        Integer total = buyAndRecharegeService.doGetBuyDataNum(request,startTime,endTime,paramKey,paramValue);
        Integer totalPage = 1;
        if (total % ValueConsts.PAGE_SIZE == 0) {
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE  + 1 ;
        }
        if (records== null ||records.size() == 0) {
            return JsonUtil.buildError("没有满足要求的纪录");
        }
        Map<String,Object> map = new HashMap<>(2);
        map.put("records",records);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

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
    @RequestMapping(value = "/telephoneCharge")
    @ResponseBody
    public String telephoneCharge(HttpServletRequest request,
                                  @RequestParam(value = ParamConsts.phone,required =true) String phone,
                                  @RequestParam(value = ParamConsts.money,required =true) Integer money,
                                  @RequestParam(value = ParamConsts.orderCode,required =true) String  orderCode,
                                  @RequestParam(value = ParamConsts.userId,required =true) Integer userId,
                                  @RequestParam(value = ParamConsts.shopId,required =true) Integer shopId) throws Exception {


        return juheService.telephoneRecharge(request,phone,money,orderCode,userId,shopId);

    }
}
