package com.aigou.oss.model.vo;

/**
 * Created by Jan on 16/6/23.
 *
 * 购买纪录
 */
public class ShowRecordVo {

    /**
     " <td>会员ID</td>" +
     " <td>会员昵称</td>" +
     " <td>会员手机</td>" +
     " <td>订单号</td>" +
     " <td>商品名</td>" +
     " <td>购买次数</td>" +
     " <td>获得云购码</td>" +
     " <td>中奖码</td>" +
     " <td>购买时间</td>" +
     */
    //会员id
    private Integer     userId;
    //会员昵称
    private String      userName;
    //会员手机
    private String      phone;
    //订单号
    private String      code;
    //商品id
    private Integer     shopId;
    //商品名及期数:第 n 期 +title
    private String      shopName;
    //购买次数
    private Integer     goNumber;
    //获得云购码 字符串
    private String      allCodes;
    //获得的云购码
    private String[]    gouCodes;
    //是否中奖(1:中奖,huode!=0,0:未中奖,huode=0)
    private Integer      lotteryOrNot;
    //中奖码
    private String      huode;
    //购买时间(格式)
    private String      time;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getGoNumber() {
        return goNumber;
    }

    public void setGoNumber(Integer goNumber) {
        this.goNumber = goNumber;
    }

    public String[] getGouCodes() {
        return gouCodes;
    }

    public void setGouCodes(String[] gouCodes) {
        this.gouCodes = gouCodes;
    }

    public Integer getLotteryOrNot() {
        return lotteryOrNot;
    }

    public void setLotteryOrNot(Integer lotteryOrNot) {
        this.lotteryOrNot = lotteryOrNot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHuode() {
        return huode;
    }

    public void setHuode(String huode) {
        this.huode = huode;
    }

    public String getAllCodes() {
        return allCodes;
    }

    public void setAllCodes(String allCodes) {
        this.allCodes = allCodes;
    }
}
