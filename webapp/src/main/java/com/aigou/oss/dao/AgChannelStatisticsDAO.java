package com.aigou.oss.dao;

import com.aigou.oss.model.AgChannelStatistics;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
@Repository
public class AgChannelStatisticsDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        AgChannelStatistics _key = new AgChannelStatistics();
        _key.setId(id);
        return getSqlSession().delete("ag_channel_statistics.deleteByPrimaryKey", _key);
    }

    public void insert(AgChannelStatistics record) {
        getSqlSession().insert("ag_channel_statistics.insert", record);
    }

    public void insertSelective(AgChannelStatistics record) {
        getSqlSession().insert("ag_channel_statistics.insertSelective", record);
    }

    public void insertBatch(List<AgChannelStatistics> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_channel_statistics.insertBatch", records);
    }

    public AgChannelStatistics selectByPrimaryKey(Long id) {
        AgChannelStatistics _key = new AgChannelStatistics();
        _key.setId(id);
        return getSqlSession().selectOne("ag_channel_statistics.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgChannelStatistics record) {
        return getSqlSession().update("ag_channel_statistics.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgChannelStatistics record) {
        return getSqlSession().update("ag_channel_statistics.updateByPrimaryKey", record);
    }

    /**
     * 查询总记录数
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public int selectCountByRelation(String channelName, String packageName, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectOne("ag_channel_statistics.selectCountByRelation", map);
    }

    /**
     * 查询历史记录
     * @param channelName
     * @param packageName
     * @param today
     * @param bounds
     * @return
     */
    public List<AgChannelStatistics> selectHistoryByRelation(String channelName, String packageName, String today, RowBounds bounds) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectList("ag_channel_statistics.selectHistoryByRelation", map, bounds);
    }

    /**
     * 查询今天数据
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public AgChannelStatistics selectToday(String channelName, String packageName, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectOne("ag_channel_statistics.selectToday", map);
    }


}