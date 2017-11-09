package com.aigou.oss.service;

import com.aigou.oss.dao.AgBannerDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.AgBanner;
import com.aigou.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/6.
 */
@Service
public class BannerService {


    @Autowired
    private AgBannerDAO agBannerDAO;
    @Autowired
    private GoShoplistDAO goShoplistDAO;

    /**
     * 获取所有banner数据
     * @param request
     * @return
     */
    public List<AgBanner> getBanneringList (HttpServletRequest request) {

        return agBannerDAO.getAllBanner();
    }

    /**
     * 获取到还不在banner中的商品
     * @param request
     * @return
     */
    public List<AgBanner>  getUnBanneredList (HttpServletRequest request) {

        return goShoplistDAO.getUnBanneredShops();
    }

    /**
     * 添加banner
     * @param request
     * @param banner
     * @return
     */
    public String addBanner (HttpServletRequest request,AgBanner banner) {

        Integer shopId = agBannerDAO.insert(banner);

        if (shopId > 0) {
            return JsonUtil.buildSuccess();
        }
        return JsonUtil.buildError("添加失败");
    }

    /**
     * 取得所有的商品(修改的时候用)
     * @param request
     * @return
     */
    public List<AgBanner> getAllShop (HttpServletRequest request) {

        return goShoplistDAO.getAllShop ();
    }

    /**
     * 修改banner
     * @param request
     * @param banner
     * @return
     */
    public String updateBanner (HttpServletRequest request,AgBanner banner) {

        AgBanner abanner = agBannerDAO.selectByPrimaryKey(banner.getBannerId());
        if (abanner == null) {
            return JsonUtil.buildError("商品不存在");
        }
        Integer row = agBannerDAO.updateByPrimaryKeySelective(banner);
        if (row==null ||row==0){
            return JsonUtil.buildError("修改失败");
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 删除多个banner纪录
     * @param request
     * @param bannerIds
     * @return
     */
    public String deleteBanners (HttpServletRequest request,String bannerIds) {

        String[] ids = bannerIds.split(",");
        for (String id : ids) {

            Integer bannerId = Integer.parseInt(id);
            agBannerDAO.deleteByPrimaryKey(bannerId);
        }
        return JsonUtil.buildSuccess();
    }

}
