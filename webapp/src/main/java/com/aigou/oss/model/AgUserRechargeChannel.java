package com.aigou.oss.model;

import java.math.*;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
public class AgUserRechargeChannel implements java.io.Serializable {

    // Fields

    private Long id;
    // 用户id
    private Integer uid;
    // 平台 0-iOS, 1-Android, 2-未知
    private Integer platform;
    // 包名
    private String packageName;
    // 渠道
    private String channelName;
    // 金额
    private BigDecimal rechargeMoney;
    // 时间
    private String createTime;

    // Constructors

    /**
     * default constructor
     */
    public AgUserRechargeChannel() {
    }

    /**
     * full constructor
     */
    public AgUserRechargeChannel(Integer uid, Integer platform, String packageName, String channelName, BigDecimal rechargeMoney, String createTime) {
        this.uid = uid;
        this.platform = platform;
        this.packageName = packageName;
        this.channelName = channelName;
        this.rechargeMoney = rechargeMoney;
        this.createTime = createTime;
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id
     */
    public Integer getUid() {
        return this.uid;
    }

    /**
     * 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 平台 0-iOS, 1-Android, 2-未知
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台 0-iOS, 1-Android, 2-未知
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 渠道
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 金额
     */
    public BigDecimal getRechargeMoney() {
        return this.rechargeMoney;
    }

    /**
     * 金额
     */
    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    /**
     * 时间
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}