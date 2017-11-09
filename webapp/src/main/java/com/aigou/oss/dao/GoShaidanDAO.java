package com.aigou.oss.dao;

import com.aigou.oss.model.GoShaidan;

import com.aigou.oss.model.GoShoplist;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
@Repository
public class GoShaidanDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer sdId) {
        GoShaidan _key = new GoShaidan();
        _key.setSdId(sdId);
        return getSqlSession().delete("go_shaidan.deleteByPrimaryKey", _key);
    }

    public void insert(GoShaidan record) {
        getSqlSession().insert("go_shaidan.insert", record);
    }

    public void insertSelective(GoShaidan record) {
        getSqlSession().insert("go_shaidan.insertSelective", record);
    }

    public void insertBatch(List<GoShaidan> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_shaidan.insertBatch", records);
    }

    public GoShaidan selectByPrimaryKey(Integer sdId) {
        GoShaidan _key = new GoShaidan();
        _key.setSdId(sdId);
        return getSqlSession().selectOne("go_shaidan.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoShaidan record) {
        return getSqlSession().update("go_shaidan.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoShaidan record) {
        return getSqlSession().update("go_shaidan.updateByPrimaryKey", record);
    }

    /**
     * 查询晒单记录
     * @param shopId 商品id
     * @param userId 用户id
     * @return
     */
    public GoShaidan selectRecordByShopIdAndUserId(Integer shopId, Integer userId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopId", shopId);
        map.put("userId", userId);
        return getSqlSession().selectOne("go_shaidan.selectRecordByShopIdAndUserId", map);
    }

    /**
     * 查询晒单数量
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param paramKey 参数名
     * @param paramValue 参数值
     * @return
     */
    public Integer selectReleaseCount(String startTime, String endTime, String paramKey, String paramValue) {

        Map<String, Object> map = new HashMap<>(4);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("paramKey", paramKey);
        map.put("paramValue", paramValue);
        return getSqlSession().selectOne("go_shaidan.selectReleaseCount", map);
    }

    /**
     * 查询晒单列表
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param paramKey 参数名
     * @param paramValue 参数值
     * @param rank 排序
     * @param bounds 分页参数
     * @return
     */
    public List<GoShaidan> selectReleaseOrder(String startTime, String endTime, String paramKey, String paramValue, String rank, RowBounds bounds) {

        Map<String, Object> map = new HashMap<>(5);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("paramKey", paramKey);
        map.put("paramValue", paramValue);
        map.put("rank", rank);
        return getSqlSession().selectList("go_shaidan.selectReleaseOrder", map, bounds);
    }





}