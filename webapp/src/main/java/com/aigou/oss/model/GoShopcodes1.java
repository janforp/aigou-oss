package com.aigou.oss.model;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-22
 */
public class GoShopcodes1 implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer sId;
    private Integer sCid;
    private Integer sLen;
    private String sCodes;
    private String sCodesTmp;

    // Constructors

    /**
     * default constructor
     */
    public GoShopcodes1() {
    }

    /**
     * full constructor
     */
    public GoShopcodes1(Integer sId, Integer sCid, Integer sLen, String sCodes, String sCodesTmp) {
        this.sId = sId;
        this.sCid = sCid;
        this.sLen = sLen;
        this.sCodes = sCodes;
        this.sCodesTmp = sCodesTmp;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSId() {
        return this.sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public Integer getSCid() {
        return this.sCid;
    }

    public void setSCid(Integer sCid) {
        this.sCid = sCid;
    }

    public Integer getSLen() {
        return this.sLen;
    }

    public void setSLen(Integer sLen) {
        this.sLen = sLen;
    }

    public String getSCodes() {
        return this.sCodes;
    }

    public void setSCodes(String sCodes) {
        this.sCodes = sCodes;
    }

    public String getSCodesTmp() {
        return this.sCodesTmp;
    }

    public void setSCodesTmp(String sCodesTmp) {
        this.sCodesTmp = sCodesTmp;
    }

}