package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-28
 */
public class AgAllowGainTimes implements java.io.Serializable {

    // Fields

    // 用户id
    private Integer uid;
    // 次数
    private Integer times;

    // Constructors

    /**
     * default constructor
     */
    public AgAllowGainTimes() {
    }

    /**
     * full constructor
     */
    public AgAllowGainTimes(Integer uid, Integer times) {
        this.uid = uid;
        this.times = times;
    }

    // Property accessors

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
     * 次数
     */
    public Integer getTimes() {
        return this.times;
    }

    /**
     * 次数
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

}