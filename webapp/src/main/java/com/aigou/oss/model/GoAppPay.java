package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-01
 */
public class GoAppPay implements java.io.Serializable {

    // Fields

    private Integer id;
    // 支付名称
    private String name;
    // 支付参数
    private String type;
    // 支付图片
    private String img;
    // 支付描述
    private String content;
    // 0关闭 1开启
    private Integer no;
    // 排序
    private Integer xu;

    // Constructors

    /**
     * default constructor
     */
    public GoAppPay() {
    }

    /**
     * full constructor
     */
    public GoAppPay(String name, String type, String img, String content, Integer no, Integer xu) {
        this.name = name;
        this.type = type;
        this.img = img;
        this.content = content;
        this.no = no;
        this.xu = xu;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 支付名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 支付名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 支付参数
     */
    public String getType() {
        return this.type;
    }

    /**
     * 支付参数
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 支付图片
     */
    public String getImg() {
        return this.img;
    }

    /**
     * 支付图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 支付描述
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 支付描述
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 0关闭 1开启
     */
    public Integer getNo() {
        return this.no;
    }

    /**
     * 0关闭 1开启
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * 排序
     */
    public Integer getXu() {
        return this.xu;
    }

    /**
     * 排序
     */
    public void setXu(Integer xu) {
        this.xu = xu;
    }

}