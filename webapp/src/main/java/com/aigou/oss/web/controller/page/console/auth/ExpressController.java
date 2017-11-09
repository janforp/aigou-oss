package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/6/17.
 * 快递信息相关
 */
@Controller
@RequestMapping(value = "/page/console/auth/express",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    /**
     * 提交快递信息
     * @param
     * @param expressName   快递公司名称
     * @param expressCode   快递单号
     * @param expressPrice  快递价格
     * @param userId        用户id
     * @param shopId        商品id
     * @param orderCode     订单号
     * @return
     */
    @RequestMapping(value = "/saveExpressInfo")
    @ResponseBody
    public String saveExpressInfo(@RequestParam(value = ParamConsts.expressName,required = true) Integer expressName,
                                  @RequestParam(value = ParamConsts.expressCode,required = true)String expressCode,
                                  @RequestParam(value = ParamConsts.expressPrice,required = true)BigDecimal expressPrice,
                                  @RequestParam(value = ParamConsts.userId,required = true)Integer userId,
                                  @RequestParam(value = ParamConsts.shopId,required = true)Integer shopId,
                                  @RequestParam(value = ParamConsts.orderCode,required = true)String  orderCode,
                                  @RequestParam(value = ParamConsts.phone,required = true)String  phone) throws UnsupportedEncodingException {


        return expressService.doSaveExpressInfo(expressName,expressCode,expressPrice,userId,shopId,orderCode,phone);
    }

}
