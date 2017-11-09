package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-06
 */
public class AgDiscoverConfig implements java.io.Serializable {

    // Fields

    // id
    private Integer discoverId;
    // 标题
    private String discoverTitle;
    // 描述
    private String discoverDescription;
    // 图片
    private String discoverImg;
    // 链接
    private String discoverUrl;
    // 排序，值越小，越靠前
    private Integer discoverOrder;

    // Constructors

    /**
     * default constructor
     */
    public AgDiscoverConfig() {
    }

    /**
     * full constructor
     */
    public AgDiscoverConfig(String discoverTitle, String discoverDescription, String discoverImg, String discoverUrl, Integer discoverOrder) {
        this.discoverTitle = discoverTitle;
        this.discoverDescription = discoverDescription;
        this.discoverImg = discoverImg;
        this.discoverUrl = discoverUrl;
        this.discoverOrder = discoverOrder;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getDiscoverId() {
        return this.discoverId;
    }

    /**
     * id
     */
    public void setDiscoverId(Integer discoverId) {
        this.discoverId = discoverId;
    }

    /**
     * 标题
     */
    public String getDiscoverTitle() {
        return this.discoverTitle;
    }

    /**
     * 标题
     */
    public void setDiscoverTitle(String discoverTitle) {
        this.discoverTitle = discoverTitle;
    }

    /**
     * 描述
     */
    public String getDiscoverDescription() {
        return this.discoverDescription;
    }

    /**
     * 描述
     */
    public void setDiscoverDescription(String discoverDescription) {
        this.discoverDescription = discoverDescription;
    }

    /**
     * 图片
     */
    public String getDiscoverImg() {
        return this.discoverImg;
    }

    /**
     * 图片
     */
    public void setDiscoverImg(String discoverImg) {
        this.discoverImg = discoverImg;
    }

    /**
     * 链接
     */
    public String getDiscoverUrl() {
        return this.discoverUrl;
    }

    /**
     * 链接
     */
    public void setDiscoverUrl(String discoverUrl) {
        this.discoverUrl = discoverUrl;
    }

    /**
     * 排序，值越小，越靠前
     */
    public Integer getDiscoverOrder() {
        return this.discoverOrder;
    }

    /**
     * 排序，值越小，越靠前
     */
    public void setDiscoverOrder(Integer discoverOrder) {
        this.discoverOrder = discoverOrder;
    }

}