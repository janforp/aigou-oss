package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
public class GoShaidan implements java.io.Serializable {

    // Fields

    // 晒单id
    private Integer sdId;
    // 用户ID
    private Integer sdUserid;
    // 商品ID
    private Integer sdShopid;
    // 商品期数
    private Integer sdQishu;

    private String sdIp;
    // 晒单标题
    private String sdTitle;
    // 缩略图
    private String sdThumbs;
    // 晒单内容
    private String sdContent;
    // 晒单图片
    private String sdPhotolist;
    // 点赞
    private Integer sdZhan;
    // 评论
    private Integer sdPing;
    // 晒单时间
    private Integer sdTime;
    private Integer sdShopsid;

    // Constructors

    /**
     * default constructor
     */
    public GoShaidan() {
    }

    /**
     * full constructor
     */
    public GoShaidan(Integer sdUserid, Integer sdShopid, Integer sdQishu, String sdIp, String sdTitle, String sdThumbs, String sdContent, String sdPhotolist, Integer sdZhan, Integer sdPing, Integer sdTime, Integer sdShopsid) {
        this.sdUserid = sdUserid;
        this.sdShopid = sdShopid;
        this.sdQishu = sdQishu;
        this.sdIp = sdIp;
        this.sdTitle = sdTitle;
        this.sdThumbs = sdThumbs;
        this.sdContent = sdContent;
        this.sdPhotolist = sdPhotolist;
        this.sdZhan = sdZhan;
        this.sdPing = sdPing;
        this.sdTime = sdTime;
        this.sdShopsid = sdShopsid;
    }

    // Property accessors

    /**
     * 晒单id
     */
    public Integer getSdId() {
        return this.sdId;
    }

    /**
     * 晒单id
     */
    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    /**
     * 用户ID
     */
    public Integer getSdUserid() {
        return this.sdUserid;
    }

    /**
     * 用户ID
     */
    public void setSdUserid(Integer sdUserid) {
        this.sdUserid = sdUserid;
    }

    /**
     * 商品ID
     */
    public Integer getSdShopid() {
        return this.sdShopid;
    }

    /**
     * 商品ID
     */
    public void setSdShopid(Integer sdShopid) {
        this.sdShopid = sdShopid;
    }

    /**
     * 商品期数
     */
    public Integer getSdQishu() {
        return this.sdQishu;
    }

    /**
     * 商品期数
     */
    public void setSdQishu(Integer sdQishu) {
        this.sdQishu = sdQishu;
    }

    public String getSdIp() {
        return this.sdIp;
    }

    public void setSdIp(String sdIp) {
        this.sdIp = sdIp;
    }

    /**
     * 晒单标题
     */
    public String getSdTitle() {
        return this.sdTitle;
    }

    /**
     * 晒单标题
     */
    public void setSdTitle(String sdTitle) {
        this.sdTitle = sdTitle;
    }

    /**
     * 缩略图
     */
    public String getSdThumbs() {
        return this.sdThumbs;
    }

    /**
     * 缩略图
     */
    public void setSdThumbs(String sdThumbs) {
        this.sdThumbs = sdThumbs;
    }

    /**
     * 晒单内容
     */
    public String getSdContent() {
        return this.sdContent;
    }

    /**
     * 晒单内容
     */
    public void setSdContent(String sdContent) {
        this.sdContent = sdContent;
    }

    /**
     * 晒单图片
     */
    public String getSdPhotolist() {
        return this.sdPhotolist;
    }

    /**
     * 晒单图片
     */
    public void setSdPhotolist(String sdPhotolist) {
        this.sdPhotolist = sdPhotolist;
    }

    /**
     * 点赞
     */
    public Integer getSdZhan() {
        return this.sdZhan;
    }

    /**
     * 点赞
     */
    public void setSdZhan(Integer sdZhan) {
        this.sdZhan = sdZhan;
    }

    /**
     * 评论
     */
    public Integer getSdPing() {
        return this.sdPing;
    }

    /**
     * 评论
     */
    public void setSdPing(Integer sdPing) {
        this.sdPing = sdPing;
    }

    /**
     * 晒单时间
     */
    public Integer getSdTime() {
        return this.sdTime;
    }

    /**
     * 晒单时间
     */
    public void setSdTime(Integer sdTime) {
        this.sdTime = sdTime;
    }

    public Integer getSdShopsid() {
        return this.sdShopsid;
    }

    public void setSdShopsid(Integer sdShopsid) {
        this.sdShopsid = sdShopsid;
    }

}