package com.aigou.oss.model.vo;

import java.util.List;

/**
 * Created by Jan on 16/7/29.
 *
 * 修改晒单页面数据
 */
public class UpdataShaidanVo {

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

    private List<String> photos;
    // 点赞
    private Integer sdZhan;
    // 评论
    private Integer sdPing;
    // 晒单时间
    private Integer sdTime;
    private Integer sdShopsid;

    public Integer getSdId() {
        return sdId;
    }

    public void setSdId(Integer sdId) {
        this.sdId = sdId;
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

    public String getSdIp() {
        return sdIp;
    }

    public void setSdIp(String sdIp) {
        this.sdIp = sdIp;
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

    public Integer getSdZhan() {
        return sdZhan;
    }

    public void setSdZhan(Integer sdZhan) {
        this.sdZhan = sdZhan;
    }

    public Integer getSdPing() {
        return sdPing;
    }

    public void setSdPing(Integer sdPing) {
        this.sdPing = sdPing;
    }

    public Integer getSdTime() {
        return sdTime;
    }

    public void setSdTime(Integer sdTime) {
        this.sdTime = sdTime;
    }

    public Integer getSdShopsid() {
        return sdShopsid;
    }

    public void setSdShopsid(Integer sdShopsid) {
        this.sdShopsid = sdShopsid;
    }
}
