package com.aigou.oss.model.vo;

import java.util.List;

/**
 * Created by Jan on 16/7/29.
 * 商品详情页面数据
 */
public class GoodsDetailVo {

    private Integer shopId;
    private String  title;
    private String  title2;
    private String  content;
    private String  picarr;
    private List<String> pics;

    public String getPicarr() {
        return picarr;
    }

    public void setPicarr(String picarr) {
        this.picarr = picarr;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
