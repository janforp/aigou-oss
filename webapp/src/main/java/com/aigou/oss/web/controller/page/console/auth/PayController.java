package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.GoAppPay;
import com.aigou.oss.model.form.AccountSaveForm;
import com.aigou.oss.model.form.PaySaveForm;
import com.aigou.oss.service.PayService;
import com.aigou.oss.util.CommonMethod;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.Md5EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jan on 16/7/1.
 *
 * 模块8 - 支付管理
 */
@Controller
@RequestMapping(value = "/page/console/auth/pay",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 跳转到支付管理页面 并显示支付方式列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/payList")
    public String  gotoPayListPageWithData (HttpServletRequest request) {

        List<GoAppPay> list = payService.getPayListData(request);

        request.setAttribute("list",list);

        return "app_pay_list";
    }

    /**
     * 添加支付方式
     * @param request
     * @param paySaveForm
     * @param result
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request, @Valid PaySaveForm paySaveForm, BindingResult result) {
        RequestContext requestContext = new RequestContext(request);

        if (payService.insert(paySaveForm, paySaveForm.getImg())) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

    /**
     * 删除选定的 支付方式
     * @Param payId
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String  deletePayById (HttpServletRequest request,
                              @RequestParam(value = ParamConsts.payIds,required = true) String payId) {

       return payService.doDeletePayById (request,payId);

    }


    /**
     * 点击修改支付方式的按钮,然后弹出对话框(用于输入修改后的信息)
     * @param request
     * @param payId
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpServletRequest request, @RequestParam(value = ParamConsts.payId, required = false) Integer payId) {
        RequestContext requestContext = new RequestContext(request);
        if (payId == null) {
            return JsonUtil.buildError(requestContext.getMessage("oss.error.pay.not.exist"));
        }
        GoAppPay pay = payService.selectById(payId);
        if (pay != null) {
            request.setAttribute("pay",pay);
            return JsonUtil.buildData(pay);
        }
        return JsonUtil.buildError(requestContext.getMessage("oss.error.pay.not.exist"));
    }

    /**
     * 点击修改框上的保存
     * @param request
     * @param pay       封装参数的对象
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update (HttpServletRequest request,GoAppPay pay) {

        return payService.updatePay (request,pay);

    }

}
