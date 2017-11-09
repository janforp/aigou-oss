package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.AgBanner;
import com.aigou.oss.service.BannerService;
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
 * banner列表功能
 */
@Controller
@RequestMapping(value = "/page/console/auth/banner",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 带着需要的数据跳转到banner页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/bannerList")
    public String gotoBannerPageWithData (HttpServletRequest request) {

        List<AgBanner> banners = bannerService.getBanneringList(request);
        //取到go_shoplist表中的商品,但是ag_banner表中没有的(只需要sid,title)
        List<AgBanner> shops = bannerService.getUnBanneredList (request);
        List<AgBanner> allShops = bannerService.getAllShop(request);

        request.setAttribute("all",allShops);
        request.setAttribute("shops",shops);
        request.setAttribute("banners",banners);
        return "banner";
    }

    /**
     * 添加banner
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addBanner (HttpServletRequest request,AgBanner banner) {

        return bannerService.addBanner(request,banner);
    }

    /**
     * 修改banner
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateBanner (HttpServletRequest request,AgBanner banner) {

        return bannerService.updateBanner(request,banner);
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

        return bannerService.deleteBanners(request,bannerIds);
    }
}
