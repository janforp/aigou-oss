package com.aigou.oss.model.vo;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/7/8.
 * 添加新商品
 */
public class AddNewGoodsVo {

    private Integer id;
    private Integer sid;
    //分类
    private Integer cateId;
    //品牌
    private Integer brandId;
    //标题
    private String title;
    //副标题
    private String title2;
    //关键字
    private String keywords;
    //描述
    private String description;
    //价格
    private BigDecimal money;
    //云购价格
    private BigDecimal yunjiage;
    //期数
    private Integer qishu;
    //最大期数
    private Integer maxqishu;
    //中奖ID区间
    private Integer quyuBegin;
    //中奖ID区间
    private Integer quyuEnd;
    //缩略图
    private String thumb;
    //是否推荐
    private Integer pos;
    //是否人气
    private Integer renqi;
    //揭晓时间
    private String  xsjxTime;
    //图片
    private String  picarr;
    //详情
    private String content;
    //广告
    private String advertisementImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQishu() {
        return qishu;
    }

    public void setQishu(Integer qishu) {
        this.qishu = qishu;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getYunjiage() {
        return yunjiage;
    }

    public void setYunjiage(BigDecimal yunjiage) {
        this.yunjiage = yunjiage;
    }

    public Integer getMaxqishu() {
        return maxqishu;
    }

    public void setMaxqishu(Integer maxqishu) {
        this.maxqishu = maxqishu;
    }

    public Integer getQuyuBegin() {
        return quyuBegin;
    }

    public void setQuyuBegin(Integer quyuBegin) {
        this.quyuBegin = quyuBegin;
    }

    public Integer getQuyuEnd() {
        return quyuEnd;
    }

    public void setQuyuEnd(Integer quyuEnd) {
        this.quyuEnd = quyuEnd;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Integer getRenqi() {
        return renqi;
    }

    public void setRenqi(Integer renqi) {
        this.renqi = renqi;
    }

    public String getXsjxTime() {
        return xsjxTime;
    }

    public void setXsjxTime(String xsjxTime) {
        this.xsjxTime = xsjxTime;
    }

    public String getPicarr() {
        return picarr;
    }

    public void setPicarr(String picarr) {
        this.picarr = picarr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdvertisementImg() {
        return advertisementImg;
    }

    public void setAdvertisementImg(String advertisementImg) {
        this.advertisementImg = advertisementImg;
    }
}
