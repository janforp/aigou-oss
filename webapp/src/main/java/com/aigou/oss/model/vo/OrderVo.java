package com.aigou.oss.model.vo;

import java.io.Serializable;

/**
 * Created by Summer on 16/6/13.
 */
public class OrderVo implements Serializable {

    private Integer shaiDanId;
    // 订单号
    private String orderCode;
    // 期数
    private Integer qishu;
    // 标题
    private String title;
    // 开奖时间
    private String q_end_time;
    // 中奖号码(幸运号码)
    private String q_user_code;
    // 中奖用户id
    private Integer userId;
    // 中奖用户昵称
    private String nickName;
    // 晒单状态
    private String status;
    // 商品id
    private Integer shopId;

    public Integer getShaiDanId() {
        return shaiDanId;
    }

    public void setShaiDanId(Integer shaiDanId) {
        this.shaiDanId = shaiDanId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getQishu() {
        return qishu;
    }

    public void setQishu(Integer qishu) {
        this.qishu = qishu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQ_end_time() {
        return q_end_time;
    }

    public void setQ_end_time(String q_end_time) {
        this.q_end_time = q_end_time;
    }

    public String getQ_user_code() {
        return q_user_code;
    }

    public void setQ_user_code(String q_user_code) {
        this.q_user_code = q_user_code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
