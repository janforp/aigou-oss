package com.aigou.oss.dao;

import com.aigou.oss.model.GoCategory;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-08-01
 */
@Repository
public class GoCategoryDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer cateid) {
        GoCategory _key = new GoCategory();
        _key.setCateid(cateid);
        return getSqlSession().delete("go_category.deleteByPrimaryKey", _key);
    }

    public void insert(GoCategory record) {
        getSqlSession().insert("go_category.insert", record);
    }

    public void insertSelective(GoCategory record) {
        getSqlSession().insert("go_category.insertSelective", record);
    }

    public void insertBatch(List<GoCategory> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_category.insertBatch", records);
    }

    public GoCategory selectByPrimaryKey(Integer cateid) {
        GoCategory _key = new GoCategory();
        _key.setCateid(cateid);
        return getSqlSession().selectOne("go_category.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoCategory record) {
        return getSqlSession().update("go_category.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoCategory record) {
        return getSqlSession().update("go_category.updateByPrimaryKey", record);
    }

    /**
     * 获取父 栏目
     * @return
     */
    public List<GoCategory> getParentCate (){
        return getSqlSession().selectList("go_category.getParentCate");
    }

    /**
     * 获取父栏目 的所有的子栏目
     * @return
     */
    public List<GoCategory> getSonCateByParentCateId(Integer parentId) {
        return getSqlSession().selectList("go_category.getSonCateByParentCateId",parentId);
    }

    /**
     * 由栏目名找
     * @param name
     * @return
     */
    public GoCategory selectCateByName(String name) {
        return getSqlSession().selectOne("go_category.selectCateByName",name);
    }
}