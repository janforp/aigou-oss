package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-04
 */
public class AgRecommendShop implements java.io.Serializable {

    // Fields

    // 同类商品唯一的id
    private Integer shopId;
    // 商品标题
    private String shopTitle;

    // Constructors

    /**
     * default constructor
     */
    public AgRecommendShop() {
    }

    /**
     * full constructor
     */
    public AgRecommendShop(Integer shopId, String shopTitle) {
        this.shopId = shopId;
        this.shopTitle = shopTitle;
    }

    // Property accessors

    /**
     * 同类商品唯一的id
     */
    public Integer getShopId() {
        return this.shopId;
    }

    /**
     * 同类商品唯一的id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * 商品标题
     */
    public String getShopTitle() {
        return this.shopTitle;
    }

    /**
     * 商品标题
     */
    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

}