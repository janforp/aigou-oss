package com.aigou.oss.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jan on 16/7/1.
 *
 * 发货页面的 中奖用户信息
 */
public class LotteryUserInfo implements Serializable {

    // 用户id
    private Integer userId;
    // 用户昵称
    private String userName;
    //手机号(go_member表)
    private String phone;
    //其它手机号(如收货地址,绑定手机号等):用于话费充值
    private String otherPhone;
    //省份城市
    private String provinceCity;
    //详细地址(为默认地址)
    private String detailAddress;


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

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }




    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }
}
