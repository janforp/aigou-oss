package com.aigou.oss.dao;

import com.aigou.oss.model.AgCategory;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-08-02
 */
@Repository
public class AgCategoryDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer cateid) {
        AgCategory _key = new AgCategory();
        _key.setCateid(cateid);
        return getSqlSession().delete("ag_category.deleteByPrimaryKey", _key);
    }

    public void insert(AgCategory record) {
        getSqlSession().insert("ag_category.insert", record);
    }

    public void insertSelective(AgCategory record) {
        getSqlSession().insert("ag_category.insertSelective", record);
    }

    public void insertBatch(List<AgCategory> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_category.insertBatch", records);
    }

    public AgCategory selectByPrimaryKey(Integer cateid) {
        AgCategory _key = new AgCategory();
        _key.setCateid(cateid);
        return getSqlSession().selectOne("ag_category.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgCategory record) {
        return getSqlSession().update("ag_category.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgCategory record) {
        return getSqlSession().update("ag_category.updateByPrimaryKey", record);
    }

    /**
     * 获取所有的栏目
     * @return
     */
    public List<AgCategory> getAllCategory(){

        return getSqlSession().selectList("ag_category.getAllCategory");
    }
}