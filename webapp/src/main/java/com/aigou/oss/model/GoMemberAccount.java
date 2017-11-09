package com.aigou.oss.model;

import java.math.BigDecimal;

/**
 * Created by Jan on 16/6/23.
 */
public class GoMemberAccount {
    /**
     uid` int(10) unsigned NOT NULL COMMENT '用户id',
     `type` tinyint(1) DEFAULT NULL COMMENT '充值1/消费-1',
     `pay` char(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
     `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '详情',
     `money` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
     `time` char(20) COLLATE utf8mb4_unicode_ci NOT NULL,
     KEY `uid` (`uid`),
     KEY `type` (`type`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='会员账户明细'
     */
    private Integer uid;

    private Integer type;

    private String  pay;

    private String  content;

    private BigDecimal  money;

    private String      time;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
