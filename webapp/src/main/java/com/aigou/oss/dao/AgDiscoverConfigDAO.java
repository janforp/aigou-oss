package com.aigou.oss.dao;

import com.aigou.oss.model.AgDiscoverConfig;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-06
 */
@Repository
public class AgDiscoverConfigDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer discoverId) {
        AgDiscoverConfig _key = new AgDiscoverConfig();
        _key.setDiscoverId(discoverId);
        return getSqlSession().delete("ag_discover_config.deleteByPrimaryKey", _key);
    }

    public Integer insert(AgDiscoverConfig record) {
        return getSqlSession().insert("ag_discover_config.insert", record);
    }

    public void insertSelective(AgDiscoverConfig record) {
        getSqlSession().insert("ag_discover_config.insertSelective", record);
    }

    public void insertBatch(List<AgDiscoverConfig> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_discover_config.insertBatch", records);
    }

    public AgDiscoverConfig selectByPrimaryKey(Integer discoverId) {
        AgDiscoverConfig _key = new AgDiscoverConfig();
        _key.setDiscoverId(discoverId);
        return getSqlSession().selectOne("ag_discover_config.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgDiscoverConfig record) {
        return getSqlSession().update("ag_discover_config.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgDiscoverConfig record) {
        return getSqlSession().update("ag_discover_config.updateByPrimaryKey", record);
    }

    /**
     * 发现表中的所有数据
     * @return
     */
    public List<AgDiscoverConfig> getAllDiscover() {
        return getSqlSession().selectList("ag_discover_config.getAllDiscover");
    }
}