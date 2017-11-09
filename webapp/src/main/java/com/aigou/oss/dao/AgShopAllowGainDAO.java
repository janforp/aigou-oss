package com.aigou.oss.dao;

import com.aigou.oss.model.AgShopAllowGain;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-21
 */
@Repository
public class AgShopAllowGainDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer shopId) {
        AgShopAllowGain _key = new AgShopAllowGain();
        _key.setShopId(shopId);
        return getSqlSession().delete("ag_shop_allow_gain.deleteByPrimaryKey", _key);
    }

    public void insert(AgShopAllowGain record) {
        getSqlSession().insert("ag_shop_allow_gain.insert", record);
    }

    public void insertSelective(AgShopAllowGain record) {
        getSqlSession().insert("ag_shop_allow_gain.insertSelective", record);
    }

    public void insertBatch(List<AgShopAllowGain> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_shop_allow_gain.insertBatch", records);
    }

    public AgShopAllowGain selectByPrimaryKey(Integer shopId) {
        AgShopAllowGain _key = new AgShopAllowGain();
        _key.setShopId(shopId);
        return getSqlSession().selectOne("ag_shop_allow_gain.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgShopAllowGain record) {
        return getSqlSession().update("ag_shop_allow_gain.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgShopAllowGain record) {
        return getSqlSession().update("ag_shop_allow_gain.updateByPrimaryKey", record);
    }

    /**
     * 获取 已经在 允许中奖表中的数据
     * @return
     */
    public List<AgShopAllowGain> getAllAllowShops (){
        return getSqlSession().selectList("ag_shop_allow_gain.getAllAllowShops");
    }
}