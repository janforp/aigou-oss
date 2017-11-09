package com.aigou.oss.dao;

import com.aigou.oss.model.AgUserRechargeChannel;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
@Repository
public class AgUserRechargeChannelDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        AgUserRechargeChannel _key = new AgUserRechargeChannel();
        _key.setId(id);
        return getSqlSession().delete("ag_user_recharge_channel.deleteByPrimaryKey", _key);
    }

    public void insert(AgUserRechargeChannel record) {
        getSqlSession().insert("ag_user_recharge_channel.insert", record);
    }

    public void insertSelective(AgUserRechargeChannel record) {
        getSqlSession().insert("ag_user_recharge_channel.insertSelective", record);
    }

    public void insertBatch(List<AgUserRechargeChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_user_recharge_channel.insertBatch", records);
    }

    public AgUserRechargeChannel selectByPrimaryKey(Long id) {
        AgUserRechargeChannel _key = new AgUserRechargeChannel();
        _key.setId(id);
        return getSqlSession().selectOne("ag_user_recharge_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgUserRechargeChannel record) {
        return getSqlSession().update("ag_user_recharge_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgUserRechargeChannel record) {
        return getSqlSession().update("ag_user_recharge_channel.updateByPrimaryKey", record);
    }

    /**
     *
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public BigDecimal selectRechargeCountByRelation(String channelName, String packageName, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectOne("ag_user_recharge_channel.selectRechargeCountByRelation", map);
    }


    /**
     * 查询每天新增用户的充值总额
     * @param time
     * @return
     */
    public  BigDecimal selectNewUserChargeDayByDay (String time) {

        return getSqlSession().selectOne("ag_user_recharge_channel.selectNewUserChargeDayByDay",time);
    }

    /**
     * 查询每天新总充值总额
     * @param time
     * @return
     */
    public  BigDecimal selectTotalUserChargeDayByDay (String time) {

        return getSqlSession().selectOne("ag_user_recharge_channel.selectTotalUserChargeDayByDay",time);
    }

    /**
     * 查询具体 包+渠道的 每天的新增用户充值额
     *
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public BigDecimal selectNewUserChargeByDayPackageChannel (String channelName, String packageName, String today) {

        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectOne("ag_user_recharge_channel.selectNewUserChargeByDayPackageChannel", map);
    }

    /**
     * 查询具体 包+渠道的 每天的 总 充值额
     *
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public BigDecimal selectTotalUserChargeByDayPackageChannel (String channelName, String packageName, String today) {

        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectOne("ag_user_recharge_channel.selectTotalUserChargeByDayPackageChannel", map);
    }

}