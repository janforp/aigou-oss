package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-11
 */
public class GoBrand implements java.io.Serializable {

    // Fields

    private Integer id;
    // 所属栏目ID
    private String cateid;
    // 显示隐藏
    private String status;
    private String name;
    private Integer order;
    private String thumb;
    private String url;

    // Constructors

    /**
     * default constructor
     */
    public GoBrand() {
    }

    /**
     * full constructor
     */
    public GoBrand(String cateid, String status, String name, Integer order, String thumb, String url) {
        this.cateid = cateid;
        this.status = status;
        this.name = name;
        this.order = order;
        this.thumb = thumb;
        this.url = url;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 所属栏目ID
     */
    public String getCateid() {
        return this.cateid;
    }

    /**
     * 所属栏目ID
     */
    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    /**
     * 显示隐藏
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 显示隐藏
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}