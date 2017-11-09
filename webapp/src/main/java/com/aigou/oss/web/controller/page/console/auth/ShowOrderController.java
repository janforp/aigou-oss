package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.model.GoShaidan;
import com.aigou.oss.model.vo.OrderVo;
import com.aigou.oss.model.vo.ReleaseListVo;
import com.aigou.oss.model.vo.UpdataShaidanVo;
import com.aigou.oss.model.vo.UserInfo;
import com.aigou.oss.service.ShowOrderService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 16/6/8.
 *
 * 晒单管理模块
 */
@RequestMapping(value = "/page/console/auth/order", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ShowOrderController {

    @Autowired
    private ShowOrderService showOrderService;

    @Autowired
    private UserService userService;

    /**
     * 晒单管理页面
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "order_list";
    }

    /**
     * 晒单列表
     *
     * @param request
     * @param pageNum
     * @param startTime
     * @param endTime
     * @param rank
     * @param paramKey
     * @param paramValue
     * @param status
     * @return
     * @throws Exception
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
                          @RequestParam(value = ParamConsts.status, defaultValue = "") String status) throws Exception {

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
        if ("0".equals(status)){ //未晒单

            // 总记录数
            Integer total = showOrderService.getUnreleaseCount(startTime, endTime, paramKey, paramValue, status);

            Integer totalPage = total / ValueConsts.PAGE_SIZE + 1;

            // 列表
            List<OrderVo> list = showOrderService.getUnreleaseList(startTime, endTime, rank, paramKey, paramValue, status, pageNum);

            Map<String, Object> map = new HashMap<>();

            map.put("totalPage", totalPage);
            map.put("list", list);

            return JsonUtil.buildData(map);

        }else if ("1".equals(status)){ //已晒单
            // 总记录数
            Integer total = showOrderService.getReleaseListCount(startTime, endTime, paramKey, paramValue, status);

            Integer totalPage = total / ValueConsts.PAGE_SIZE + 1;

            // 列表
            List<ReleaseListVo> list = showOrderService.getReleaseList(startTime, endTime, rank, paramKey, paramValue, status, pageNum);

            Map<String, Object> map = new HashMap<>();

            map.put("totalPage", totalPage);
            map.put("list", list);

            return JsonUtil.buildData(map);
        }
        return JsonUtil.buildError("请选择晒单状态");
    }

    /**
     * 检查是否晒过单
     *
     * @param request
     * @param shopId
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public String check(HttpServletRequest request,
                        @RequestParam(value = ParamConsts.shopId, defaultValue = "") Integer shopId,
                        @RequestParam(value = ParamConsts.userId, defaultValue = "") Integer userId) throws Exception {

        GoShaidan record = showOrderService.selectRecordByShopIdAndUserId(shopId, userId);

        if(record == null) { // 未晒单
            return JsonUtil.buildSuccess();
        }

        return JsonUtil.buildError("已晒单");

    }

    /**
     * 晒单页面
     *
     * @return
     */
    @RequestMapping(value = "/release_page")
    public String release_page(HttpServletRequest request,
                               @RequestParam(value = ParamConsts.shopId, defaultValue = "") Integer shopId,
                               @RequestParam(value = ParamConsts.userId, defaultValue = "") Integer userId) {

        UserInfo user = userService.selectUser(userId);

        request.setAttribute("user", user);
        request.setAttribute("shopId", shopId);


        return "release_page";

    }

    /**
     * 修改晒单页面
     *
     * @return
     */
    @RequestMapping(value = "/updateShaidanPage")
    public String release_page(HttpServletRequest request,
                               @RequestParam(value = ParamConsts.shopId, defaultValue = "") Integer shopId,
                               @RequestParam(value = ParamConsts.userId, defaultValue = "") Integer userId,
                               @RequestParam(value = ParamConsts.sdId, required = true) Integer sdId) {

        UpdataShaidanVo shaidan = userService.getSUpdataShaidanVoById (request,sdId);

        UserInfo user = userService.selectUser(userId);
        if (StringUtil.isEmpty(sdId)){
            sdId = null;
        }


        request.setAttribute("shaidan", shaidan);
        request.setAttribute("user", user);
        request.setAttribute("shopId", shopId);
        request.setAttribute("sdId",sdId);


        return "update_shaidan";

    }

    /**
     * 修改用户信息
     *
     * @param request
     * @param userName
     * @param userPhoto
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update_user")
    @ResponseBody
    public String update_user(HttpServletRequest request,
                              @RequestParam(value = ParamConsts.userName, defaultValue = "") String userName,
                              @RequestParam(value = ParamConsts.userPhoto, defaultValue = "") String userPhoto,
                              @RequestParam(value = ParamConsts.userId, defaultValue = "") Integer userId) throws Exception {

        if(StringUtil.isEmpty(userName)) {
            userName = null;
        }

        if(StringUtil.isEmpty(userPhoto)) {
            userPhoto = null;
        }

        int result = userService.updateUser(userId, userName, userPhoto);

        if(result == 1) {
            return JsonUtil.buildSuccess();
        }

        return JsonUtil.buildError();

    }

    /**
     * 晒单
     *
     * @param request
     * @param shopId
     * @param userId
     * @param title
     * @param content
     * @param releaseImg
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/release")
    @ResponseBody
    public String release(HttpServletRequest request,
                          @RequestParam(value = ParamConsts.shopId, defaultValue = "") Integer shopId,
                          @RequestParam(value = ParamConsts.userId, defaultValue = "") Integer userId,
                          @RequestParam(value = ParamConsts.title, defaultValue = "") String title,
                          @RequestParam(value = ParamConsts.content, defaultValue = "") String content,
                          @RequestParam(value = ParamConsts.releaseImg, defaultValue = "") String releaseImg) throws Exception {

        GoShaidan record = showOrderService.selectRecordByShopIdAndUserId(shopId, userId);

        if(record != null) { // 已晒单
            return JsonUtil.buildError("已晒单");
        }

        return showOrderService.release(shopId, userId, title, content, releaseImg);

    }
    /**
     * 要删除商品id的用&连接的字符串
     * @param request
     * @param sdIds
     * @return
     */
    @RequestMapping(value = "/deleteShaidan")
    @ResponseBody
    public String deleteShaidan (HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.sdIds, required = true) String sdIds) {

        return showOrderService.doDeleteShaidan (request,sdIds);
    }

    /**
     * 修改晒单
     * @param request
     * @param shaidanId 晒单ID
     * @param shopId    商品ID
     * @param userId    用户ID
     * @param title
     * @param content
     * @param releaseImg
     * @return
     */
    @RequestMapping(value = "/updateShaidan")
    @ResponseBody
    public String updateShaidan (HttpServletRequest request,
                                 @RequestParam(value = ParamConsts.shaidanId,required = true) Integer shaidanId,
                                 @RequestParam(value = ParamConsts.shopId, defaultValue = "") Integer shopId,
                                 @RequestParam(value = ParamConsts.userId, defaultValue = "") Integer userId,
                                 @RequestParam(value = ParamConsts.title, defaultValue = "") String title,
                                 @RequestParam(value = ParamConsts.content, defaultValue = "") String content,
                                 @RequestParam(value = ParamConsts.releaseImg, defaultValue = "") String releaseImg) {


        GoShaidan record = showOrderService.selectRecordByShopIdAndUserId(shopId, userId);

        if(record == null) { // 已晒单
            return JsonUtil.buildError("先晒单");
        }
        return showOrderService.doUpdateShaidan (shaidanId,shopId, userId, title, content, releaseImg);
    }

}
