package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.AgDiscoverConfig;
import com.aigou.oss.service.DiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/6.
 *
 * 发现
 */
@Controller
@RequestMapping(value = "/page/console/auth/discover",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class DiscoverController {

    @Autowired
    private DiscoverService discoverService;

    /**
     * 跳转到发现页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/discoverList")
    public String gotoDiscoverPageWithData(HttpServletRequest request) {

        List<AgDiscoverConfig> discovers = discoverService.getAllDiscover(request);
        request.setAttribute("discovers",discovers);

        return "discover";
    }

    /**
     * 添加discover
     * @param request
     * @param discover
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addBanner (HttpServletRequest request,AgDiscoverConfig discover) {

        return discoverService.addCover(request,discover);
    }

    /**
     * 修改discover
     * @param request
     * @param discover
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateBanner (HttpServletRequest request,AgDiscoverConfig discover) {

        return discoverService.updateDiscover(request,discover);
    }

    /**
     * 删除banner纪录
     * @param request
     * @param bannerIds
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteBanner (HttpServletRequest request,String bannerIds) {

        return discoverService.deleteDiscovers(request,bannerIds);
    }
}
