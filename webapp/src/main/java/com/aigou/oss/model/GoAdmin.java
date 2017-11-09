package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-11
 */
public class GoAdmin implements java.io.Serializable {

    // Fields

    private Integer uid;
    private Integer mid;
    private String username;
    private String userpass;
    private String useremail;
    private Integer addtime;
    private Integer logintime;
    private String loginip;

    // Constructors

    /**
     * default constructor
     */
    public GoAdmin() {
    }

    /**
     * full constructor
     */
    public GoAdmin(Integer mid, String username, String userpass, String useremail, Integer addtime, Integer logintime, String loginip) {
        this.mid = mid;
        this.username = username;
        this.userpass = userpass;
        this.useremail = useremail;
        this.addtime = addtime;
        this.logintime = logintime;
        this.loginip = loginip;
    }

    // Property accessors

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return this.mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return this.userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUseremail() {
        return this.useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Integer getAddtime() {
        return this.addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getLogintime() {
        return this.logintime;
    }

    public void setLogintime(Integer logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return this.loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

}