package com.aigou.oss.model.vo;

import java.util.List;

/**
 * Created by Jan on 16/7/29.
 *
 * 已晒单列表数据
 */
public class ReleaseListVo {

    // 晒单id
    private Integer sdId;
    private Integer userId;
    //商品名
    private String shopTitle;
    // 用户ID
    private Integer sdUserid;
    // 商品ID
    private Integer sdShopid;
    // 商品期数
    private Integer sdQishu;
    // 晒单标题
    private String sdTitle;
    // 缩略图
    private String sdThumbs;
    // 晒单内容
    private String sdContent;
    // 晒单图片
    private String sdPhotolist;
    //晒单图片 集合
    private List<String> photos;
    // 晒单时间
    private Integer sdTime;
    //2016-7-25 12:0:0
    private String  sdTimeShow;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public Integer getSdUserid() {
        return sdUserid;
    }

    public void setSdUserid(Integer sdUserid) {
        this.sdUserid = sdUserid;
    }

    public Integer getSdShopid() {
        return sdShopid;
    }

    public void setSdShopid(Integer sdShopid) {
        this.sdShopid = sdShopid;
    }

    public Integer getSdQishu() {
        return sdQishu;
    }

    public void setSdQishu(Integer sdQishu) {
        this.sdQishu = sdQishu;
    }

    public String getSdTitle() {
        return sdTitle;
    }

    public void setSdTitle(String sdTitle) {
        this.sdTitle = sdTitle;
    }

    public String getSdThumbs() {
        return sdThumbs;
    }

    public void setSdThumbs(String sdThumbs) {
        this.sdThumbs = sdThumbs;
    }

    public String getSdContent() {
        return sdContent;
    }

    public void setSdContent(String sdContent) {
        this.sdContent = sdContent;
    }

    public String getSdPhotolist() {
        return sdPhotolist;
    }

    public void setSdPhotolist(String sdPhotolist) {
        this.sdPhotolist = sdPhotolist;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Integer getSdTime() {
        return sdTime;
    }

    public void setSdTime(Integer sdTime) {
        this.sdTime = sdTime;
    }

    public String getSdTimeShow() {
        return sdTimeShow;
    }

    public void setSdTimeShow(String sdTimeShow) {
        this.sdTimeShow = sdTimeShow;
    }
}
