package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.AgRecommendShop;
import com.aigou.oss.service.RecommendService;
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
 * Created by Jan on 16/7/4.
 *
 * 模块9-推荐
 */
@Controller
@RequestMapping(value = "/page/console/auth/recommend",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 跳转到推荐页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/recommend")
    public String gotoRecommendPageWithData (HttpServletRequest request) {

        List<AgRecommendShop> shops = recommendService.getAllRecommend(request);

        List<AgRecommendShop> recommends = recommendService.getRecommendShopInShopList(request);

        request.setAttribute("recommends",recommends);

        request.setAttribute("lists",shops);

        return "recommend";
    }

    /**
     * 删除
     * @param request
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteRecommend (HttpServletRequest request,
                                   @RequestParam(value = ParamConsts.sid,required = true) Integer shopId) {

        AgRecommendShop shop = recommendService.selectShopByShopId (request,shopId);
        if (shop == null) {
            return JsonUtil.buildError("商品不在推荐中");
        }
        Integer affect = recommendService.deleteRecommend(request,shopId);

        if (affect == null || affect == 0) {
            return JsonUtil.buildError("删除失败");
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 新增
     * @param request
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addRecommend (HttpServletRequest request,
                                @RequestParam(value = ParamConsts.sid,required = true) Integer shopId,
                                @RequestParam(value = ParamConsts.title,required = true) String shopTitle) {

        AgRecommendShop shop = recommendService.selectShopByShopId(request,shopId);
        if (shop != null) {
            return JsonUtil.buildError("商品已在推荐中");
        }
        recommendService.addRecommend(request,shopId,shopTitle);

        return JsonUtil.buildSuccess();
    }
}
