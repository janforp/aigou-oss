package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-21
 */
public class AgInnerAccount implements java.io.Serializable {

    // Fields

    // 用户id
    private Integer userId;

    // Constructors

    /**
     * default constructor
     */
    public AgInnerAccount() {
    }

    /**
     * full constructor
     */
    public AgInnerAccount(Integer userId) {
        this.userId = userId;
    }

    // Property accessors

    /**
     * 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}