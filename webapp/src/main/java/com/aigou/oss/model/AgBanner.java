package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-06
 */
public class AgBanner implements java.io.Serializable {

    // Fields

    // id
    private Integer bannerId;
    // 标题
    private String bannerTitle;
    // 图片
    private String bannerImg;
    // 链接
    private String bannerUrl;
    // 同一商品id
    private Integer shopId;
    // 排序，值越小，越靠前
    private Integer bannerOrder;

    // Constructors

    /**
     * default constructor
     */
    public AgBanner() {
    }

    /**
     * full constructor
     */
    public AgBanner(String bannerTitle, String bannerImg, String bannerUrl, Integer shopId, Integer bannerOrder) {
        this.bannerTitle = bannerTitle;
        this.bannerImg = bannerImg;
        this.bannerUrl = bannerUrl;
        this.shopId = shopId;
        this.bannerOrder = bannerOrder;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getBannerId() {
        return this.bannerId;
    }

    /**
     * id
     */
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * 标题
     */
    public String getBannerTitle() {
        return this.bannerTitle;
    }

    /**
     * 标题
     */
    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    /**
     * 图片
     */
    public String getBannerImg() {
        return this.bannerImg;
    }

    /**
     * 图片
     */
    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    /**
     * 链接
     */
    public String getBannerUrl() {
        return this.bannerUrl;
    }

    /**
     * 链接
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 同一商品id
     */
    public Integer getShopId() {
        return this.shopId;
    }

    /**
     * 同一商品id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * 排序，值越小，越靠前
     */
    public Integer getBannerOrder() {
        return this.bannerOrder;
    }

    /**
     * 排序，值越小，越靠前
     */
    public void setBannerOrder(Integer bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

}