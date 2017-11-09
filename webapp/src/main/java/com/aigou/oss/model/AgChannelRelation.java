package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
public class AgChannelRelation implements java.io.Serializable {

    // Fields

    // 包名
    private String packageName;
    // 渠道名
    private String channelName;
    // 关系名
    private String relationName;

    // Constructors

    /**
     * default constructor
     */
    public AgChannelRelation() {
    }

    /**
     * full constructor
     */
    public AgChannelRelation(String packageName, String channelName, String relationName) {
        this.packageName = packageName;
        this.channelName = channelName;
        this.relationName = relationName;
    }

    // Property accessors

    /**
     * 包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 渠道名
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 渠道名
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 关系名
     */
    public String getRelationName() {
        return this.relationName;
    }

    /**
     * 关系名
     */
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

}