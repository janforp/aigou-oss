package com.aigou.oss.dao;

import com.aigou.oss.model.AgRandomTimes;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-22
 */
@Repository
public class AgRandomTimesDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer buyTimes) {
        AgRandomTimes _key = new AgRandomTimes();
        _key.setBuyTimes(buyTimes);
        return getSqlSession().delete("ag_random_times.deleteByPrimaryKey", _key);
    }

    public void insert(AgRandomTimes record) {
        getSqlSession().insert("ag_random_times.insert", record);
    }

    public void insertSelective(AgRandomTimes record) {
        getSqlSession().insert("ag_random_times.insertSelective", record);
    }

    public void insertBatch(List<AgRandomTimes> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_random_times.insertBatch", records);
    }

    public AgRandomTimes selectByPrimaryKey(Integer buyTimes) {
        AgRandomTimes _key = new AgRandomTimes();
        _key.setBuyTimes(buyTimes);
        return getSqlSession().selectOne("ag_random_times.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgRandomTimes record) {
        return getSqlSession().update("ag_random_times.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgRandomTimes record) {
        return getSqlSession().update("ag_random_times.updateByPrimaryKey", record);
    }

    /**
     * 获取随机购买数
     * @return
     */
    public int selectByRandom() {
        return getSqlSession().selectOne("ag_random_times.selectByRandom");
    }

}