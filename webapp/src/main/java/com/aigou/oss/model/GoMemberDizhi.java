package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-17
 */
public class GoMemberDizhi implements java.io.Serializable {

    // Fields

    // id
    private Integer id;
    // 用户id
    private Integer uid;
    // 省
    private String sheng;
    // 市
    private String shi;
    // 县
    private String xian;
    // 街道地址
    private String jiedao;
    // 邮编
    private Integer youbian;
    // 收货人
    private String shouhuoren;
    // 手机
    private String mobile;
    // QQ
    private String qq;
    // 座机号
    private String tell;
    // 是否默认
    private String defaul;
    private Integer time;
    private String mappointLng;
    private String mappointLat;

    // Constructors

    /**
     * default constructor
     */
    public GoMemberDizhi() {
    }

    /**
     * full constructor
     */
    public GoMemberDizhi(Integer uid, String sheng, String shi, String xian, String jiedao, Integer youbian, String shouhuoren, String mobile, String qq, String tell, String defaul, Integer time, String mappointLng, String mappointLat) {
        this.uid = uid;
        this.sheng = sheng;
        this.shi = shi;
        this.xian = xian;
        this.jiedao = jiedao;
        this.youbian = youbian;
        this.shouhuoren = shouhuoren;
        this.mobile = mobile;
        this.qq = qq;
        this.tell = tell;
        this.defaul = defaul;
        this.time = time;
        this.mappointLng = mappointLng;
        this.mappointLat = mappointLat;
    }

    // Property accessors

    /**
     * id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
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
     * 省
     */
    public String getSheng() {
        return this.sheng;
    }

    /**
     * 省
     */
    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    /**
     * 市
     */
    public String getShi() {
        return this.shi;
    }

    /**
     * 市
     */
    public void setShi(String shi) {
        this.shi = shi;
    }

    /**
     * 县
     */
    public String getXian() {
        return this.xian;
    }

    /**
     * 县
     */
    public void setXian(String xian) {
        this.xian = xian;
    }

    /**
     * 街道地址
     */
    public String getJiedao() {
        return this.jiedao;
    }

    /**
     * 街道地址
     */
    public void setJiedao(String jiedao) {
        this.jiedao = jiedao;
    }

    /**
     * 邮编
     */
    public Integer getYoubian() {
        return this.youbian;
    }

    /**
     * 邮编
     */
    public void setYoubian(Integer youbian) {
        this.youbian = youbian;
    }

    /**
     * 收货人
     */
    public String getShouhuoren() {
        return this.shouhuoren;
    }

    /**
     * 收货人
     */
    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren;
    }

    /**
     * 手机
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * QQ
     */
    public String getQq() {
        return this.qq;
    }

    /**
     * QQ
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 座机号
     */
    public String getTell() {
        return this.tell;
    }

    /**
     * 座机号
     */
    public void setTell(String tell) {
        this.tell = tell;
    }

    /**
     * 是否默认
     */
    public String getDefaul() {
        return this.defaul;
    }

    /**
     * 是否默认
     */
    public void setDefaul(String defaul) {
        this.defaul = defaul;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMappointLng() {
        return this.mappointLng;
    }

    public void setMappointLng(String mappointLng) {
        this.mappointLng = mappointLng;
    }

    public String getMappointLat() {
        return this.mappointLat;
    }

    public void setMappointLat(String mappointLat) {
        this.mappointLat = mappointLat;
    }

}