package com.aigou.oss.model.vo;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/7/1.
 *
 * 查看某天的 各包,渠道的 新增用户/充值/总充值
 */
public class PackageChannelDetailVo {

    //日期(2016-7-1)
    private String          time;
    //包名
    private String          packageName;
    //渠道名
    private String          channelName;
    //当日新增用户
    private Integer         dayNewUser;
    //当日新增用户总充值
    private BigDecimal      dayNewUserCharge;
    //当日总充值(所用用户)
    private BigDecimal      dayTotalCharge;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getDayNewUser() {
        return dayNewUser;
    }

    public void setDayNewUser(Integer dayNewUser) {
        this.dayNewUser = dayNewUser;
    }

    public BigDecimal getDayNewUserCharge() {
        return dayNewUserCharge;
    }

    public void setDayNewUserCharge(BigDecimal dayNewUserCharge) {
        this.dayNewUserCharge = dayNewUserCharge;
    }

    public BigDecimal getDayTotalCharge() {
        return dayTotalCharge;
    }

    public void setDayTotalCharge(BigDecimal dayTotalCharge) {
        this.dayTotalCharge = dayTotalCharge;
    }
}
