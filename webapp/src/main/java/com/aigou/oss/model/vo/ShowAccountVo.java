package com.aigou.oss.model.vo;

/**
 * Created by Jan on 16/6/23.
 *
 * 充值纪录
 */
public class ShowAccountVo {


    //用户id
    private Integer userId;
    //昵称
    private String  userName;
    //用户手机
    private String  phone;
    //充值,消费
    private String  type;
    //积分/福分/帐户
    private String  pay;
    //详情
    private String  content;
    //金额 消费带"-" 充值 带"+"
    private String   money;
    //时间
    private String   time;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
