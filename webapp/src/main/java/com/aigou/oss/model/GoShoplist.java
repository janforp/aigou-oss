package com.aigou.oss.model;

import java.math.*;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
public class GoShoplist implements java.io.Serializable {

    // Fields

    // 商品id
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
    // 商品内容详情
    private String content;
    private String codesTable;
    private Integer xsjxTime;
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

    // Constructors

    /**
     * default constructor
     */
    public GoShoplist() {
    }

    /**
     * full constructor
     */
    public GoShoplist(Integer sid, Integer cateid, Integer brandid, String title, String titleStyle, String title2, String keywords, String description, BigDecimal money, BigDecimal yunjiage, Integer zongrenshu, Integer canyurenshu, Integer shenyurenshu, Integer defRenshu, Integer qishu, Integer maxqishu, String thumb, String picarr, String content, String codesTable, Integer xsjxTime, Integer pos, Integer renqi, Integer time, Integer order, Integer qUid, String qUser, String qUserCode, String qContent, String qCounttime, String qEndTime, String qShowtime, Integer renqipos, Integer newpos, Integer bannershop, String posthumb, Integer quyu, Integer quyuBegin, Integer quyuEnd) {
        this.sid = sid;
        this.cateid = cateid;
        this.brandid = brandid;
        this.title = title;
        this.titleStyle = titleStyle;
        this.title2 = title2;
        this.keywords = keywords;
        this.description = description;
        this.money = money;
        this.yunjiage = yunjiage;
        this.zongrenshu = zongrenshu;
        this.canyurenshu = canyurenshu;
        this.shenyurenshu = shenyurenshu;
        this.defRenshu = defRenshu;
        this.qishu = qishu;
        this.maxqishu = maxqishu;
        this.thumb = thumb;
        this.picarr = picarr;
        this.content = content;
        this.codesTable = codesTable;
        this.xsjxTime = xsjxTime;
        this.pos = pos;
        this.renqi = renqi;
        this.time = time;
        this.order = order;
        this.qUid = qUid;
        this.qUser = qUser;
        this.qUserCode = qUserCode;
        this.qContent = qContent;
        this.qCounttime = qCounttime;
        this.qEndTime = qEndTime;
        this.qShowtime = qShowtime;
        this.renqipos = renqipos;
        this.newpos = newpos;
        this.bannershop = bannershop;
        this.posthumb = posthumb;
        this.quyu = quyu;
        this.quyuBegin = quyuBegin;
        this.quyuEnd = quyuEnd;
    }

    // Property accessors

    /**
     * 商品id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 商品id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 同一个商品
     */
    public Integer getSid() {
        return this.sid;
    }

    /**
     * 同一个商品
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 所属栏目ID
     */
    public Integer getCateid() {
        return this.cateid;
    }

    /**
     * 所属栏目ID
     */
    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    /**
     * 所属品牌ID
     */
    public Integer getBrandid() {
        return this.brandid;
    }

    /**
     * 所属品牌ID
     */
    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    /**
     * 商品标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 商品标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleStyle() {
        return this.titleStyle;
    }

    public void setTitleStyle(String titleStyle) {
        this.titleStyle = titleStyle;
    }

    /**
     * 副标题
     */
    public String getTitle2() {
        return this.title2;
    }

    /**
     * 副标题
     */
    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 金额
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 云购人次价格
     */
    public BigDecimal getYunjiage() {
        return this.yunjiage;
    }

    /**
     * 云购人次价格
     */
    public void setYunjiage(BigDecimal yunjiage) {
        this.yunjiage = yunjiage;
    }

    /**
     * 总需人数
     */
    public Integer getZongrenshu() {
        return this.zongrenshu;
    }

    /**
     * 总需人数
     */
    public void setZongrenshu(Integer zongrenshu) {
        this.zongrenshu = zongrenshu;
    }

    /**
     * 已参与人数
     */
    public Integer getCanyurenshu() {
        return this.canyurenshu;
    }

    /**
     * 已参与人数
     */
    public void setCanyurenshu(Integer canyurenshu) {
        this.canyurenshu = canyurenshu;
    }

    public Integer getShenyurenshu() {
        return this.shenyurenshu;
    }

    public void setShenyurenshu(Integer shenyurenshu) {
        this.shenyurenshu = shenyurenshu;
    }

    public Integer getDefRenshu() {
        return this.defRenshu;
    }

    public void setDefRenshu(Integer defRenshu) {
        this.defRenshu = defRenshu;
    }

    /**
     * 期数
     */
    public Integer getQishu() {
        return this.qishu;
    }

    /**
     * 期数
     */
    public void setQishu(Integer qishu) {
        this.qishu = qishu;
    }

    /**
     *  最大期数
     */
    public Integer getMaxqishu() {
        return this.maxqishu;
    }

    /**
     *  最大期数
     */
    public void setMaxqishu(Integer maxqishu) {
        this.maxqishu = maxqishu;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     * 商品图片
     */
    public String getPicarr() {
        return this.picarr;
    }

    /**
     * 商品图片
     */
    public void setPicarr(String picarr) {
        this.picarr = picarr;
    }

    /**
     * 商品内容详情
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 商品内容详情
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getCodesTable() {
        return this.codesTable;
    }

    public void setCodesTable(String codesTable) {
        this.codesTable = codesTable;
    }

    public Integer getXsjxTime() {
        return this.xsjxTime;
    }

    public void setXsjxTime(Integer xsjxTime) {
        this.xsjxTime = xsjxTime;
    }

    /**
     * 是否推荐
     */
    public Integer getPos() {
        return this.pos;
    }

    /**
     * 是否推荐
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    /**
     * 是否人气商品0否1是
     */
    public Integer getRenqi() {
        return this.renqi;
    }

    /**
     * 是否人气商品0否1是
     */
    public void setRenqi(Integer renqi) {
        this.renqi = renqi;
    }

    /**
     * 时间
     */
    public Integer getTime() {
        return this.time;
    }

    /**
     * 时间
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 中奖人ID
     */
    public Integer getQUid() {
        return this.qUid;
    }

    /**
     * 中奖人ID
     */
    public void setQUid(Integer qUid) {
        this.qUid = qUid;
    }

    /**
     * 中奖人信息
     */
    public String getQUser() {
        return this.qUser;
    }

    /**
     * 中奖人信息
     */
    public void setQUser(String qUser) {
        this.qUser = qUser;
    }

    /**
     * 中奖码
     */
    public String getQUserCode() {
        return this.qUserCode;
    }

    /**
     * 中奖码
     */
    public void setQUserCode(String qUserCode) {
        this.qUserCode = qUserCode;
    }

    /**
     * 揭晓内容
     */
    public String getQContent() {
        return this.qContent;
    }

    /**
     * 揭晓内容
     */
    public void setQContent(String qContent) {
        this.qContent = qContent;
    }

    /**
     * 总时间相加
     */
    public String getQCounttime() {
        return this.qCounttime;
    }

    /**
     * 总时间相加
     */
    public void setQCounttime(String qCounttime) {
        this.qCounttime = qCounttime;
    }

    /**
     * 揭晓时间
     */
    public String getQEndTime() {
        return this.qEndTime;
    }

    /**
     * 揭晓时间
     */
    public void setQEndTime(String qEndTime) {
        this.qEndTime = qEndTime;
    }

    /**
     * Y/N揭晓动画
     */
    public String getQShowtime() {
        return this.qShowtime;
    }

    /**
     * Y/N揭晓动画
     */
    public void setQShowtime(String qShowtime) {
        this.qShowtime = qShowtime;
    }

    public Integer getRenqipos() {
        return this.renqipos;
    }

    public void setRenqipos(Integer renqipos) {
        this.renqipos = renqipos;
    }

    public Integer getNewpos() {
        return this.newpos;
    }

    public void setNewpos(Integer newpos) {
        this.newpos = newpos;
    }

    public Integer getBannershop() {
        return this.bannershop;
    }

    public void setBannershop(Integer bannershop) {
        this.bannershop = bannershop;
    }

    public String getPosthumb() {
        return this.posthumb;
    }

    public void setPosthumb(String posthumb) {
        this.posthumb = posthumb;
    }

    public Integer getQuyu() {
        return this.quyu;
    }

    public void setQuyu(Integer quyu) {
        this.quyu = quyu;
    }

    public Integer getQuyuBegin() {
        return this.quyuBegin;
    }

    public void setQuyuBegin(Integer quyuBegin) {
        this.quyuBegin = quyuBegin;
    }

    public Integer getQuyuEnd() {
        return this.quyuEnd;
    }

    public void setQuyuEnd(Integer quyuEnd) {
        this.quyuEnd = quyuEnd;
    }

}