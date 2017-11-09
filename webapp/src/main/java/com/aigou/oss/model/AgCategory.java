package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-08-02
 */
public class AgCategory implements java.io.Serializable {

    // Fields

    // 栏目id
    private Integer cateid;
    // 栏目名称
    private String name;
    // 图片地址
    private String url;
    // 排序
    private Integer order;

    // Constructors

    /**
     * default constructor
     */
    public AgCategory() {
    }

    /**
     * full constructor
     */
    public AgCategory(String name, String url, Integer order) {
        this.name = name;
        this.url = url;
        this.order = order;
    }

    // Property accessors

    /**
     * 栏目id
     */
    public Integer getCateid() {
        return this.cateid;
    }

    /**
     * 栏目id
     */
    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    /**
     * 栏目名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 栏目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 图片地址
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 图片地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 排序
     */
    public Integer getOrder() {
        return this.order;
    }

    /**
     * 排序
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

}