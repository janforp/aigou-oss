package com.aigou.oss.dao;

import com.aigou.oss.model.AgParamConfig;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-21
 */
@Repository
public class AgParamConfigDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String paramType) {
        AgParamConfig _key = new AgParamConfig();
        _key.setParamType(paramType);
        return getSqlSession().delete("ag_param_config.deleteByPrimaryKey", _key);
    }

    public void insert(AgParamConfig record) {
        getSqlSession().insert("ag_param_config.insert", record);
    }

    public void insertSelective(AgParamConfig record) {
        getSqlSession().insert("ag_param_config.insertSelective", record);
    }

    public void insertBatch(List<AgParamConfig> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_param_config.insertBatch", records);
    }

    public AgParamConfig selectByPrimaryKey(String paramType) {
        AgParamConfig _key = new AgParamConfig();
        _key.setParamType(paramType);
        return getSqlSession().selectOne("ag_param_config.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgParamConfig record) {
        return getSqlSession().update("ag_param_config.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgParamConfig record) {
        return getSqlSession().update("ag_param_config.updateByPrimaryKey", record);
    }
}