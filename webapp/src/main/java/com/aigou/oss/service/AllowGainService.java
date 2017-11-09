package com.aigou.oss.service;

import com.aigou.oss.dao.AgShopAllowGainDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.AgShopAllowGain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/5.
 *
 *   模块10 - 允许中奖模块
 */
@Service
public class AllowGainService {

    @Autowired
    private AgShopAllowGainDAO agShopAllowGainDAO;
    @Autowired
    private GoShoplistDAO goShoplistDAO;


    public AgShopAllowGain selectShopByShopId (HttpServletRequest request,Integer shopId) {
        return agShopAllowGainDAO.selectByPrimaryKey(shopId);
    }
    /**
     * 获取 已经在 允许中奖表中的数据
     * @param request
     * @return
     */
    public List<AgShopAllowGain> getAllAllowShops(HttpServletRequest request) {

        return agShopAllowGainDAO.getAllAllowShops();
    }

    /**
     * 从商品表获取 正在出售未开奖的商品
     * @param request
     * @return
     */
    public List<AgShopAllowGain> getAllowShopInShopList (HttpServletRequest request) {

        return goShoplistDAO.getAllowShopInShopList();
    }

    /**
     * 删除允许中奖商品
     * @param request
     * @param shopId
     * @return
     */
    public Integer deleteAllow (HttpServletRequest request,Integer shopId) {

        return agShopAllowGainDAO.deleteByPrimaryKey(shopId);
    }

    /**
     * 添加  允许中奖 商品
     * @param request
     * @param shopId
     * @return
     */
    public void addAllow (HttpServletRequest request,Integer shopId,String shopTitle) {

        AgShopAllowGain shop = new AgShopAllowGain();
        shop.setShopId(shopId);
        shop.setShopTitle(shopTitle);

        agShopAllowGainDAO.insert(shop);
    }

}
