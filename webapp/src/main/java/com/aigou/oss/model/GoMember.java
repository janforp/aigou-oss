package com.aigou.oss.model;

import org.phprpc.util.PHPSerializer;

import java.math.*;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
public class GoMember implements java.io.Serializable {

    // Fields

    private Integer uid;
    // 用户名
    private String username;
    // 用户邮箱
    private String email;
    // 用户手机
    private String mobile;
    // 密码
    private String password;
    private String userIp;
    // 用户头像
    private String img;
    // 用户签名
    private String qianming;
    // 用户权限组
    private Integer groupid;
    // 用户加入的圈子组1|2|3
    private String addgroup;
    // 账户金额
    private BigDecimal money;
    private BigDecimal yongjin;
    // 邮箱认证码
    private String emailcode;
    // 手机认证码
    private String mobilecode;
    // 找会密码认证码-1,1,码
    private String passcode;
    // 注册参数
    private String regKey;
    private Integer score;
    private Integer jingyan;
    private Integer yaoqing;
    private String band;
    private Integer time;
    private Integer loginTime;
    private Integer signInTime;
    // 上次签到日期
    private String signInDate;
    private Integer signInTimeAll;
    private Integer autoUser;
    private Integer h2;
    private Integer h3;
    private Integer h4;
    private Integer h5;
    private BigDecimal y1;
    private BigDecimal y2;
    private BigDecimal y3;
    private BigDecimal y4;
    private BigDecimal y5;

    // Constructors

    /**
     * default constructor
     */
    public GoMember() {
    }

    /**
     * full constructor
     */
    public GoMember(String username, String email, String mobile, String password, String userIp, String img, String qianming, Integer groupid, String addgroup, BigDecimal money, BigDecimal yongjin, String emailcode, String mobilecode, String passcode, String regKey, Integer score, Integer jingyan, Integer yaoqing, String band, Integer time, Integer loginTime, Integer signInTime, String signInDate, Integer signInTimeAll, Integer autoUser, Integer h2, Integer h3, Integer h4, Integer h5, BigDecimal y1, BigDecimal y2, BigDecimal y3, BigDecimal y4, BigDecimal y5) {
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.userIp = userIp;
        this.img = img;
        this.qianming = qianming;
        this.groupid = groupid;
        this.addgroup = addgroup;
        this.money = money;
        this.yongjin = yongjin;
        this.emailcode = emailcode;
        this.mobilecode = mobilecode;
        this.passcode = passcode;
        this.regKey = regKey;
        this.score = score;
        this.jingyan = jingyan;
        this.yaoqing = yaoqing;
        this.band = band;
        this.time = time;
        this.loginTime = loginTime;
        this.signInTime = signInTime;
        this.signInDate = signInDate;
        this.signInTimeAll = signInTimeAll;
        this.autoUser = autoUser;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.h5 = h5;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
    }

    // Property accessors

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 用户名
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 用户手机
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 用户手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserIp() {
        return this.userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    /**
     * 用户头像
     */
    public String getImg() {
        return this.img;
    }

    /**
     * 用户头像
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 用户签名
     */
    public String getQianming() {
        return this.qianming;
    }

    /**
     * 用户签名
     */
    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    /**
     * 用户权限组
     */
    public Integer getGroupid() {
        return this.groupid;
    }

    /**
     * 用户权限组
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 用户加入的圈子组1|2|3
     */
    public String getAddgroup() {
        return this.addgroup;
    }

    /**
     * 用户加入的圈子组1|2|3
     */
    public void setAddgroup(String addgroup) {
        this.addgroup = addgroup;
    }

    /**
     * 账户金额
     */
    public BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 账户金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getYongjin() {
        return this.yongjin;
    }

    public void setYongjin(BigDecimal yongjin) {
        this.yongjin = yongjin;
    }

    /**
     * 邮箱认证码
     */
    public String getEmailcode() {
        return this.emailcode;
    }

    /**
     * 邮箱认证码
     */
    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
    }

    /**
     * 手机认证码
     */
    public String getMobilecode() {
        return this.mobilecode;
    }

    /**
     * 手机认证码
     */
    public void setMobilecode(String mobilecode) {
        this.mobilecode = mobilecode;
    }

    /**
     * 找会密码认证码-1,1,码
     */
    public String getPasscode() {
        return this.passcode;
    }

    /**
     * 找会密码认证码-1,1,码
     */
    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    /**
     * 注册参数
     */
    public String getRegKey() {
        return this.regKey;
    }

    /**
     * 注册参数
     */
    public void setRegKey(String regKey) {
        this.regKey = regKey;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getJingyan() {
        return this.jingyan;
    }

    public void setJingyan(Integer jingyan) {
        this.jingyan = jingyan;
    }

    public Integer getYaoqing() {
        return this.yaoqing;
    }

    public void setYaoqing(Integer yaoqing) {
        this.yaoqing = yaoqing;
    }

    public String getBand() {
        return this.band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getSignInTime() {
        return this.signInTime;
    }

    public void setSignInTime(Integer signInTime) {
        this.signInTime = signInTime;
    }

    /**
     * 上次签到日期
     */
    public String getSignInDate() {
        return this.signInDate;
    }

    /**
     * 上次签到日期
     */
    public void setSignInDate(String signInDate) {
        this.signInDate = signInDate;
    }

    public Integer getSignInTimeAll() {
        return this.signInTimeAll;
    }

    public void setSignInTimeAll(Integer signInTimeAll) {
        this.signInTimeAll = signInTimeAll;
    }

    public Integer getAutoUser() {
        return this.autoUser;
    }

    public void setAutoUser(Integer autoUser) {
        this.autoUser = autoUser;
    }

    public Integer getH2() {
        return this.h2;
    }

    public void setH2(Integer h2) {
        this.h2 = h2;
    }

    public Integer getH3() {
        return this.h3;
    }

    public void setH3(Integer h3) {
        this.h3 = h3;
    }

    public Integer getH4() {
        return this.h4;
    }

    public void setH4(Integer h4) {
        this.h4 = h4;
    }

    public Integer getH5() {
        return this.h5;
    }

    public void setH5(Integer h5) {
        this.h5 = h5;
    }

    public BigDecimal getY1() {
        return this.y1;
    }

    public void setY1(BigDecimal y1) {
        this.y1 = y1;
    }

    public BigDecimal getY2() {
        return this.y2;
    }

    public void setY2(BigDecimal y2) {
        this.y2 = y2;
    }

    public BigDecimal getY3() {
        return this.y3;
    }

    public void setY3(BigDecimal y3) {
        this.y3 = y3;
    }

    public BigDecimal getY4() {
        return this.y4;
    }

    public void setY4(BigDecimal y4) {
        this.y4 = y4;
    }

    public BigDecimal getY5() {
        return this.y5;
    }

    public void setY5(BigDecimal y5) {
        this.y5 = y5;
    }

    public static String getSerialize(GoMember obj) throws Exception {

        PHPSerializer p = new PHPSerializer();

        return new String(p.serialize(obj), "UTF-8");

    }

}