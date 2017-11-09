package com.aigou.oss.model;

import java.math.*;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
public class GoMemberGoRecord implements java.io.Serializable {

    // Fields

    private Integer id;
    // 订单号
    private String code;
    // 相同订单
    private Integer codeTmp;
    private String username;
    private String uphoto;
    // 会员id
    private Integer uid;
    // 商品id
    private Integer shopid;
    // 商品名
    private String shopname;
    // 期数
    private Integer shopqishu;
    // 购买次数
    private Integer gonumber;
    // 云购码
    private String goucode;
    private BigDecimal moneycount;
    // 中奖码
    private String huode;
    // 付款方式
    private String payType;
    private String ip;
    // 订单状态
    private String status;
    private BigDecimal companyMoney;
    private String companyCode;
    private String company;
    // 购买时间
    private String time;

    // Constructors

    /**
     * default constructor
     */
    public GoMemberGoRecord() {
    }

    /**
     * full constructor
     */
    public GoMemberGoRecord(String code, Integer codeTmp, String username, String uphoto, Integer uid, Integer shopid, String shopname, Integer shopqishu, Integer gonumber, String goucode, BigDecimal moneycount, String huode, String payType, String ip, String status, BigDecimal companyMoney, String companyCode, String company, String time) {
        this.code = code;
        this.codeTmp = codeTmp;
        this.username = username;
        this.uphoto = uphoto;
        this.uid = uid;
        this.shopid = shopid;
        this.shopname = shopname;
        this.shopqishu = shopqishu;
        this.gonumber = gonumber;
        this.goucode = goucode;
        this.moneycount = moneycount;
        this.huode = huode;
        this.payType = payType;
        this.ip = ip;
        this.status = status;
        this.companyMoney = companyMoney;
        this.companyCode = companyCode;
        this.company = company;
        this.time = time;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 订单号
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 订单号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 相同订单
     */
    public Integer getCodeTmp() {
        return this.codeTmp;
    }

    /**
     * 相同订单
     */
    public void setCodeTmp(Integer codeTmp) {
        this.codeTmp = codeTmp;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUphoto() {
        return this.uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    /**
     * 会员id
     */
    public Integer getUid() {
        return this.uid;
    }

    /**
     * 会员id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 商品id
     */
    public Integer getShopid() {
        return this.shopid;
    }

    /**
     * 商品id
     */
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    /**
     * 商品名
     */
    public String getShopname() {
        return this.shopname;
    }

    /**
     * 商品名
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    /**
     * 期数
     */
    public Integer getShopqishu() {
        return this.shopqishu;
    }

    /**
     * 期数
     */
    public void setShopqishu(Integer shopqishu) {
        this.shopqishu = shopqishu;
    }

    /**
     * 购买次数
     */
    public Integer getGonumber() {
        return this.gonumber;
    }

    /**
     * 购买次数
     */
    public void setGonumber(Integer gonumber) {
        this.gonumber = gonumber;
    }

    /**
     * 云购码
     */
    public String getGoucode() {
        return this.goucode;
    }

    /**
     * 云购码
     */
    public void setGoucode(String goucode) {
        this.goucode = goucode;
    }

    public BigDecimal getMoneycount() {
        return this.moneycount;
    }

    public void setMoneycount(BigDecimal moneycount) {
        this.moneycount = moneycount;
    }

    /**
     * 中奖码
     */
    public String getHuode() {
        return this.huode;
    }

    /**
     * 中奖码
     */
    public void setHuode(String huode) {
        this.huode = huode;
    }

    /**
     * 付款方式
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * 付款方式
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 订单状态
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 订单状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCompanyMoney() {
        return this.companyMoney;
    }

    public void setCompanyMoney(BigDecimal companyMoney) {
        this.companyMoney = companyMoney;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 购买时间
     */
    public String getTime() {
        return this.time;
    }

    /**
     * 购买时间
     */
    public void setTime(String time) {
        this.time = time;
    }

}