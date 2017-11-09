package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-22
 */
public class AgRandomTimes implements java.io.Serializable {

    // Fields

    // 购买数
    private Integer buyTimes;

    // Constructors

    /**
     * default constructor
     */
    public AgRandomTimes() {
    }

    /**
     * full constructor
     */
    public AgRandomTimes(Integer buyTimes) {
        this.buyTimes = buyTimes;
    }

    // Property accessors

    /**
     * 购买数
     */
    public Integer getBuyTimes() {
        return this.buyTimes;
    }

    /**
     * 购买数
     */
    public void setBuyTimes(Integer buyTimes) {
        this.buyTimes = buyTimes;
    }

}