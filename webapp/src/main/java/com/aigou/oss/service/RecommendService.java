package com.aigou.oss.service;

import com.aigou.oss.dao.AgRecommendShopDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.AgRecommendShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/4.
 *
 * 模块9-推荐
 */
@Service
public class RecommendService {

    @Autowired
    private AgRecommendShopDAO agRecommendShopDAO;
    @Autowired
    private GoShoplistDAO goShoplistDAO;

    public AgRecommendShop selectShopByShopId (HttpServletRequest request,Integer shopId) {
        return agRecommendShopDAO.selectByPrimaryKey(shopId);
    }
    /**
     * 跳转到推荐页面
     * @param request
     * @return
     */
    public List<AgRecommendShop> getAllRecommend (HttpServletRequest request) {

        return agRecommendShopDAO.getAllRecommend();

    }

    /**
     * 删除推荐商品
     * @param request
     * @param shopId
     * @return
     */
    public Integer deleteRecommend (HttpServletRequest request,Integer shopId) {

        return agRecommendShopDAO.deleteByPrimaryKey(shopId);
    }

    /**
     * 获得推荐商品,用于添加推荐商品
     * @param request
     * @return
     */
    public List<AgRecommendShop> getRecommendShopInShopList(HttpServletRequest request) {

        return goShoplistDAO.getRecommendShopInShopList();
    }

    /**
     * 添加推荐商品
     * @param request
     * @param shopId
     * @return
     */
    public void addRecommend (HttpServletRequest request,Integer shopId,String shopTitle) {

        AgRecommendShop shop = new AgRecommendShop();
        shop.setShopId(shopId);
        shop.setShopTitle(shopTitle);

        agRecommendShopDAO.insert(shop);
    }


}
