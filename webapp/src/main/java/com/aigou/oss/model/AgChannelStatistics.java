package com.aigou.oss.model;

import java.math.*;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
public class AgChannelStatistics implements java.io.Serializable {

    // Fields

    private Long id;
    // 包名
    private String packageName;
    // 渠道名
    private String channelName;
    // 当日新增人数
    private Integer dayPeople;
    // 当日新增充值
    private BigDecimal dayRecharge;
    // 日期
    private String dayTime;

    // Constructors

    /**
     * default constructor
     */
    public AgChannelStatistics() {
    }

    /**
     * full constructor
     */
    public AgChannelStatistics(String packageName, String channelName, Integer dayPeople, BigDecimal dayRecharge, String dayTime) {
        this.packageName = packageName;
        this.channelName = channelName;
        this.dayPeople = dayPeople;
        this.dayRecharge = dayRecharge;
        this.dayTime = dayTime;
    }

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
     * 渠道名
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道名
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 当日新增人数
     */
    public Integer getDayPeople() {
        return this.dayPeople;
    }

    /**
     * 当日新增人数
     */
    public void setDayPeople(Integer dayPeople) {
        this.dayPeople = dayPeople;
    }

    /**
     * 当日新增充值
     */
    public BigDecimal getDayRecharge() {
        return this.dayRecharge;
    }

    /**
     * 当日新增充值
     */
    public void setDayRecharge(BigDecimal dayRecharge) {
        this.dayRecharge = dayRecharge;
    }

    /**
     * 日期
     */
    public String getDayTime() {
        return this.dayTime;
    }

    /**
     * 日期
     */
    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

}