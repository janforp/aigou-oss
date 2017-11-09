package com.aigou.oss.dao;

import com.aigou.oss.model.AgBanner;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-06
 */
@Repository
public class AgBannerDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer bannerId) {
        AgBanner _key = new AgBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().delete("ag_banner.deleteByPrimaryKey", _key);
    }

    public Integer insert(AgBanner record) {
        return getSqlSession().insert("ag_banner.insert", record);
    }

    public void insertSelective(AgBanner record) {
        getSqlSession().insert("ag_banner.insertSelective", record);
    }

    public void insertBatch(List<AgBanner> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_banner.insertBatch", records);
    }

    public AgBanner selectByPrimaryKey(Integer bannerId) {
        AgBanner _key = new AgBanner();
        _key.setBannerId(bannerId);
        return getSqlSession().selectOne("ag_banner.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgBanner record) {
        return getSqlSession().update("ag_banner.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgBanner record) {
        return getSqlSession().update("ag_banner.updateByPrimaryKey", record);
    }

    /**
     * 获取所有banner数据
     * @return
     */
    public List<AgBanner> getAllBanner () {

        return getSqlSession().selectList("ag_banner.getAllBanner");
    }

}