package com.aigou.oss.model.vo;

import com.aigou.oss.model.GoMemberGoRecord;

import java.util.List;

/**
 * 中奖码
 * Created by Summer on 16/5/25.
 */
public class ToCode implements java.io.Serializable {

    // 中奖码
    private String q_code;
    // 计算详情
    private String q_content;
    // 时间因子和
    private Long count_time;
    // 最近100条纪录
    private List<GoMemberGoRecord> lastList;

    public String getQ_code() {
        return q_code;
    }

    public void setQ_code(String q_code) {
        this.q_code = q_code;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public Long getCount_time() {
        return count_time;
    }

    public void setCount_time(Long count_time) {
        this.count_time = count_time;
    }

    public List<GoMemberGoRecord> getLastList() {
        return lastList;
    }

    public void setLastList(List<GoMemberGoRecord> lastList) {
        this.lastList = lastList;
    }
}