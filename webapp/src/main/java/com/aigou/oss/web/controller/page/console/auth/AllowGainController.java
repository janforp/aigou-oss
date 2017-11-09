package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.AgShopAllowGain;
import com.aigou.oss.service.AllowGainService;
import com.aigou.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/5.
 *
 * 模块10 - 允许中奖模块
 */
@Controller
@RequestMapping(value = "/page/console/auth/allow",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class AllowGainController {

    @Autowired
    private AllowGainService allowGainService;


    /**
     * 跳转到推荐页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/allow")
    public String gotoRecommendPageWithData (HttpServletRequest request) {
        //允许中奖的商品
        List<AgShopAllowGain> shops = allowGainService.getAllAllowShops(request);

        List<AgShopAllowGain> recommends = allowGainService.getAllowShopInShopList(request);

        request.setAttribute("recommends",recommends);

        request.setAttribute("lists",shops);

        return "allow_gain";
    }

    /**
     * 设为不能中奖
     * @param request
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteRecommend (HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.sid,required = true) Integer shopId) {

        AgShopAllowGain shop = allowGainService.selectShopByShopId (request,shopId);
        if (shop == null) {
            return JsonUtil.buildError("商品不在此范围");
        }
        Integer affect = allowGainService.deleteAllow(request,shopId);

        if (affect == null || affect == 0) {
            return JsonUtil.buildError("设置失败");
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 设为可以中奖
     * @param request
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addRecommend (HttpServletRequest request,
                                @RequestParam(value = ParamConsts.sid,required = true) Integer shopId,
                                @RequestParam(value = ParamConsts.title,required = true) String shopTitle) {

        AgShopAllowGain shop = allowGainService.selectShopByShopId(request,shopId);
        if (shop != null) {
            return JsonUtil.buildError("商品已在允许中奖列");
        }
        allowGainService.addAllow(request,shopId,shopTitle);

        return JsonUtil.buildSuccess();
    }
}
