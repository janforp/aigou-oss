package com.aigou.oss.model;

import java.math.*;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-27
 */
public class GoMemberAddmoneyRecord implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer uid;
    private String code;
    private BigDecimal money;
    private String payType;
    private String status;
    private Integer time;
    private Integer score;
    // 购物车cookie
    private String scookies;
    private Integer hbmoney;

    // Constructors

    /**
     * default constructor
     */
    public GoMemberAddmoneyRecord() {
    }

    /**
     * full constructor
     */
    public GoMemberAddmoneyRecord(Integer uid, String code, BigDecimal money, String payType, String status, Integer time, Integer score, String scookies, Integer hbmoney) {
        this.uid = uid;
        this.code = code;
        this.money = money;
        this.payType = payType;
        this.status = status;
        this.time = time;
        this.score = score;
        this.scookies = scookies;
        this.hbmoney = hbmoney;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 购物车cookie
     */
    public String getScookies() {
        return this.scookies;
    }

    /**
     * 购物车cookie
     */
    public void setScookies(String scookies) {
        this.scookies = scookies;
    }

    public Integer getHbmoney() {
        return this.hbmoney;
    }

    public void setHbmoney(Integer hbmoney) {
        this.hbmoney = hbmoney;
    }

}