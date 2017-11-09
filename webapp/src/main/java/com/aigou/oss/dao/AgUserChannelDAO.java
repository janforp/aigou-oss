package com.aigou.oss.dao;

import com.aigou.oss.model.AgUserChannel;

import com.aigou.oss.model.vo.DayStatisticsVo;
import com.aigou.oss.model.vo.PackageChannelDetailVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-16
 */
@Repository
public class AgUserChannelDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Long id) {
        AgUserChannel _key = new AgUserChannel();
        _key.setId(id);
        return getSqlSession().delete("ag_user_channel.deleteByPrimaryKey", _key);
    }

    public void insert(AgUserChannel record) {
        getSqlSession().insert("ag_user_channel.insert", record);
    }

    public void insertSelective(AgUserChannel record) {
        getSqlSession().insert("ag_user_channel.insertSelective", record);
    }

    public void insertBatch(List<AgUserChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_user_channel.insertBatch", records);
    }

    public AgUserChannel selectByPrimaryKey(Long id) {
        AgUserChannel _key = new AgUserChannel();
        _key.setId(id);
        return getSqlSession().selectOne("ag_user_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgUserChannel record) {
        return getSqlSession().update("ag_user_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgUserChannel record) {
        return getSqlSession().update("ag_user_channel.updateByPrimaryKey", record);
    }

    /**
     * 查询当日新增用户数
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public Integer selectPeopleCountByRelation(String channelName, String packageName, String today) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("channelName", channelName);
        map.put("packageName", packageName);
        map.put("today", today);
        return getSqlSession().selectOne("ag_user_channel.selectPeopleCountByRelation", map);
    }

    /**
     * --查询每天的新增用户--
     * @param bounds
     * @return
     */
    public List<DayStatisticsVo> selectDayStatisticsDayByDay (RowBounds bounds) {

        return getSqlSession().selectList("ag_user_channel.selectDayStatisticsDayByDay",null,bounds);

    }

    /**
     * --查询每天的新增用户--纪录的总数   用于分页:纪录中的总日期数
     * @return
     */
    public Integer selectDayStatisticsDayByDayNums () {

        return getSqlSession().selectOne("ag_user_channel.selectDayStatisticsDayByDayNums");
    }

    /**
     * 查询出所有不同的包名+渠道名 的组合
     * @return
     */
    public List<PackageChannelDetailVo> getAllPackageChannelCombination () {

        return getSqlSession().selectList("ag_user_channel.getAllPackageChannelCombination");
    }

}