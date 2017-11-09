package com.aigou.oss.dao;

import com.aigou.oss.model.AgRecommendShop;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-04
 */
@Repository
public class AgRecommendShopDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer shopId) {
        AgRecommendShop _key = new AgRecommendShop();
        _key.setShopId(shopId);
        return getSqlSession().delete("ag_recommend_shop.deleteByPrimaryKey", _key);
    }

    public void insert(AgRecommendShop record) {
        getSqlSession().insert("ag_recommend_shop.insert", record);
    }

    public void insertSelective(AgRecommendShop record) {
        getSqlSession().insert("ag_recommend_shop.insertSelective", record);
    }

    public void insertBatch(List<AgRecommendShop> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_recommend_shop.insertBatch", records);
    }

    public AgRecommendShop selectByPrimaryKey(Integer shopId) {
        AgRecommendShop _key = new AgRecommendShop();
        _key.setShopId(shopId);
        return getSqlSession().selectOne("ag_recommend_shop.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgRecommendShop record) {
        return getSqlSession().update("ag_recommend_shop.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgRecommendShop record) {
        return getSqlSession().update("ag_recommend_shop.updateByPrimaryKey", record);
    }


    public List<AgRecommendShop> getAllRecommend () {
        return getSqlSession().selectList("ag_recommend_shop.getAllRecommend");
    }
}