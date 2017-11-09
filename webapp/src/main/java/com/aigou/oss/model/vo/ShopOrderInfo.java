package com.aigou.oss.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/6/17.
 *
 * 订单商品详情
 */
public class ShopOrderInfo implements Serializable{

    //商品名
    private String      title;
    //剩余次数
    private Integer     shengyuNums;
    //总需次数
    private Integer     totalNums;
    //商品期数
    private Integer     qishu;
    //商品价格
    private BigDecimal price;
    //中奖人name
    private String      userName;
    //中奖人ID
    private Integer     userId;
    //购买人邮箱
    private String      email;
    //购买人手机
    private String      phone;
    //中奖云购码
    private String      lotteryCode;
    //当前订单购买次数
    private Integer      buyNums;
    //所有获得的云够码
    private String[]    totalBuyCodes;
    //购买时间(2016-06-08 16:58:42)
    private String      buyTime;
    //收货地址
    private String      dizhi;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getShengyuNums() {
        return shengyuNums;
    }

    public void setShengyuNums(Integer shengyuNums) {
        this.shengyuNums = shengyuNums;
    }

    public Integer getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(Integer totalNums) {
        this.totalNums = totalNums;
    }

    public Integer getQishu() {
        return qishu;
    }

    public void setQishu(Integer qishu) {
        this.qishu = qishu;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public Integer getBuyNums() {
        return buyNums;
    }

    public void setBuyNums(Integer buyNums) {
        this.buyNums = buyNums;
    }

    public String[] getTotalBuyCodes() {
        return totalBuyCodes;
    }

    public void setTotalBuyCodes(String[] totalBuyCodes) {
        this.totalBuyCodes = totalBuyCodes;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }
}
