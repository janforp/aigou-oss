package com.aigou.oss.dao;

import com.aigou.oss.model.AgChannelRelation;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
@Repository
public class AgChannelRelationDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String packageName, String channelName) {
        AgChannelRelation _key = new AgChannelRelation();
        _key.setPackageName(packageName);
        _key.setChannelName(channelName);
        return getSqlSession().delete("ag_channel_relation.deleteByPrimaryKey", _key);
    }

    public void insert(AgChannelRelation record) {
        getSqlSession().insert("ag_channel_relation.insert", record);
    }

    public void insertSelective(AgChannelRelation record) {
        getSqlSession().insert("ag_channel_relation.insertSelective", record);
    }

    public void insertBatch(List<AgChannelRelation> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_channel_relation.insertBatch", records);
    }

    public AgChannelRelation selectByPrimaryKey(String packageName, String channelName) {
        AgChannelRelation _key = new AgChannelRelation();
        _key.setPackageName(packageName);
        _key.setChannelName(channelName);
        return getSqlSession().selectOne("ag_channel_relation.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgChannelRelation record) {
        return getSqlSession().update("ag_channel_relation.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgChannelRelation record) {
        return getSqlSession().update("ag_channel_relation.updateByPrimaryKey", record);
    }

    /**
     * 查询所有渠道关系
     * @return
     */
    public List<AgChannelRelation> selectAllRelation() {
        return getSqlSession().selectList("ag_channel_relation.selectAllRelation");
    }

    /**
     * 查询渠道关系
     * @param relationName
     * @return
     */
    public AgChannelRelation selectRelation(String relationName) {
        return getSqlSession().selectOne("ag_channel_relation.selectRelation", relationName);
    }


}