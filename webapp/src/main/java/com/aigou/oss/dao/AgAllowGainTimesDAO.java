package com.aigou.oss.dao;

import com.aigou.oss.model.AgAllowGainTimes;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-28
 */
@Repository
public class AgAllowGainTimesDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer uid) {
        AgAllowGainTimes _key = new AgAllowGainTimes();
        _key.setUid(uid);
        return getSqlSession().delete("ag_allow_gain_times.deleteByPrimaryKey", _key);
    }

    public void insert(AgAllowGainTimes record) {
        getSqlSession().insert("ag_allow_gain_times.insert", record);
    }

    public void insertSelective(AgAllowGainTimes record) {
        getSqlSession().insert("ag_allow_gain_times.insertSelective", record);
    }

    public void insertBatch(List<AgAllowGainTimes> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_allow_gain_times.insertBatch", records);
    }

    public AgAllowGainTimes selectByPrimaryKey(Integer uid) {
        AgAllowGainTimes _key = new AgAllowGainTimes();
        _key.setUid(uid);
        return getSqlSession().selectOne("ag_allow_gain_times.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgAllowGainTimes record) {
        return getSqlSession().update("ag_allow_gain_times.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgAllowGainTimes record) {
        return getSqlSession().update("ag_allow_gain_times.updateByPrimaryKey", record);
    }

    public AgAllowGainTimes selectLockByUid(Integer uid) {
        return getSqlSession().selectOne("ag_allow_gain_times.selectLockByUid", uid);
    }

}