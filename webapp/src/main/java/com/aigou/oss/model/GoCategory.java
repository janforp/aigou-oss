package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-08-01
 */
public class GoCategory implements java.io.Serializable {

    // Fields

    // 栏目id
    private Integer cateid;
    // 父ID
    private Integer parentid;
    private Integer channel;
    // 栏目模型
    private Integer model;
    // 栏目名称
    private String name;
    // 英文名
    private String catdir;
    private String url;
    private String info;
    // 排序
    private Integer order;
    private String cids;
    private Integer html;

    // Constructors

    /**
     * default constructor
     */
    public GoCategory() {
    }

    /**
     * full constructor
     */
    public GoCategory(Integer cateid, Integer parentid, Integer channel, Integer model, String name, String catdir, String url, String info, Integer order, String cids, Integer html) {
        this.cateid = cateid;
        this.parentid = parentid;
        this.channel = channel;
        this.model = model;
        this.name = name;
        this.catdir = catdir;
        this.url = url;
        this.info = info;
        this.order = order;
        this.cids = cids;
        this.html = html;
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
     * 父ID
     */
    public Integer getParentid() {
        return this.parentid;
    }

    /**
     * 父ID
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getChannel() {
        return this.channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * 栏目模型
     */
    public Integer getModel() {
        return this.model;
    }

    /**
     * 栏目模型
     */
    public void setModel(Integer model) {
        this.model = model;
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
     * 英文名
     */
    public String getCatdir() {
        return this.catdir;
    }

    /**
     * 英文名
     */
    public void setCatdir(String catdir) {
        this.catdir = catdir;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public String getCids() {
        return this.cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public Integer getHtml() {
        return this.html;
    }

    public void setHtml(Integer html) {
        this.html = html;
    }

}