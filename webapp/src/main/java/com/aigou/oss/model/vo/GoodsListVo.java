package com.aigou.oss.model.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/7/28.
 * 商品列表页面数据
 */
public class GoodsListVo {

    //进行中:1,最大期数满且都结束:0
    private Integer status;

    private String statusShow;

    private Integer id;
    // 同一个商品
    private Integer sid;
    // 所属栏目ID
    private Integer cateid;
    // 所属品牌ID
    private Integer brandid;
    // 商品标题
    private String title;
    private String titleStyle;
    // 副标题
    private String title2;
    private String keywords;
    private String description;
    // 金额
    private BigDecimal money;
    // 云购人次价格
    private BigDecimal yunjiage;
    // 总需人数
    private Integer zongrenshu;
    // 已参与人数
    private Integer canyurenshu;
    private Integer shenyurenshu;
    private Integer defRenshu;
    // 期数
    private Integer qishu;
    //  最大期数
    private Integer maxqishu;
    private String thumb;
    // 商品图片
    private String picarr;
    //图片url
    private List<String > pics;
    // 商品内容详情
    private String content;
    private String codesTable;
    private Integer xsjxTime;
    //先是揭晓2016-7-26 12:0:0
    private String xsjxTimeShow;
    // 是否推荐
    private Integer pos;
    // 是否人气商品0否1是
    private Integer renqi;
    // 时间
    private Integer time;
    private Integer order;
    // 中奖人ID
    private Integer qUid;
    // 中奖人信息
    private String qUser;
    // 中奖码
    private String qUserCode;
    // 揭晓内容
    private String qContent;
    // 总时间相加
    private String qCounttime;
    // 揭晓时间
    private String qEndTime;
    // Y/N揭晓动画
    private String qShowtime;
    private Integer renqipos;
    private Integer newpos;
    private Integer bannershop;
    private String posthumb;
    private Integer quyu;
    private Integer quyuBegin;
    private Integer quyuEnd;

    public String getStatusShow() {
        return statusShow;
    }

    public void setStatusShow(String statusShow) {
        this.statusShow = statusShow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getXsjxTimeShow() {
        return xsjxTimeShow;
    }

    public void setXsjxTimeShow(String xsjxTimeShow) {
        this.xsjxTimeShow = xsjxTimeShow;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(String titleStyle) {
        this.titleStyle = titleStyle;
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

    public Integer getZongrenshu() {
        return zongrenshu;
    }

    public void setZongrenshu(Integer zongrenshu) {
        this.zongrenshu = zongrenshu;
    }

    public Integer getCanyurenshu() {
        return canyurenshu;
    }

    public void setCanyurenshu(Integer canyurenshu) {
        this.canyurenshu = canyurenshu;
    }

    public Integer getShenyurenshu() {
        return shenyurenshu;
    }

    public void setShenyurenshu(Integer shenyurenshu) {
        this.shenyurenshu = shenyurenshu;
    }

    public Integer getDefRenshu() {
        return defRenshu;
    }

    public void setDefRenshu(Integer defRenshu) {
        this.defRenshu = defRenshu;
    }

    public Integer getQishu() {
        return qishu;
    }

    public void setQishu(Integer qishu) {
        this.qishu = qishu;
    }

    public Integer getMaxqishu() {
        return maxqishu;
    }

    public void setMaxqishu(Integer maxqishu) {
        this.maxqishu = maxqishu;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
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

    public String getCodesTable() {
        return codesTable;
    }

    public void setCodesTable(String codesTable) {
        this.codesTable = codesTable;
    }

    public Integer getXsjxTime() {
        return xsjxTime;
    }

    public void setXsjxTime(Integer xsjxTime) {
        this.xsjxTime = xsjxTime;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getqUid() {
        return qUid;
    }

    public void setqUid(Integer qUid) {
        this.qUid = qUid;
    }

    public String getqUser() {
        return qUser;
    }

    public void setqUser(String qUser) {
        this.qUser = qUser;
    }

    public String getqUserCode() {
        return qUserCode;
    }

    public void setqUserCode(String qUserCode) {
        this.qUserCode = qUserCode;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getqCounttime() {
        return qCounttime;
    }

    public void setqCounttime(String qCounttime) {
        this.qCounttime = qCounttime;
    }

    public String getqEndTime() {
        return qEndTime;
    }

    public void setqEndTime(String qEndTime) {
        this.qEndTime = qEndTime;
    }

    public String getqShowtime() {
        return qShowtime;
    }

    public void setqShowtime(String qShowtime) {
        this.qShowtime = qShowtime;
    }

    public Integer getRenqipos() {
        return renqipos;
    }

    public void setRenqipos(Integer renqipos) {
        this.renqipos = renqipos;
    }

    public Integer getNewpos() {
        return newpos;
    }

    public void setNewpos(Integer newpos) {
        this.newpos = newpos;
    }

    public Integer getBannershop() {
        return bannershop;
    }

    public void setBannershop(Integer bannershop) {
        this.bannershop = bannershop;
    }

    public String getPosthumb() {
        return posthumb;
    }

    public void setPosthumb(String posthumb) {
        this.posthumb = posthumb;
    }

    public Integer getQuyu() {
        return quyu;
    }

    public void setQuyu(Integer quyu) {
        this.quyu = quyu;
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
}
