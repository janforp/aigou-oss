package com.aigou.oss.dao;

import com.aigou.oss.model.GoBrand;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-11
 */
@Repository
public class GoBrandDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoBrand _key = new GoBrand();
        _key.setId(id);
        return getSqlSession().delete("go_brand.deleteByPrimaryKey", _key);
    }

    public void insert(GoBrand record) {
        getSqlSession().insert("go_brand.insert", record);
    }

    public void insertSelective(GoBrand record) {
        getSqlSession().insert("go_brand.insertSelective", record);
    }

    public void insertBatch(List<GoBrand> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_brand.insertBatch", records);
    }

    public GoBrand selectByPrimaryKey(Integer id) {
        GoBrand _key = new GoBrand();
        _key.setId(id);
        return getSqlSession().selectOne("go_brand.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoBrand record) {
        return getSqlSession().update("go_brand.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoBrand record) {
        return getSqlSession().update("go_brand.updateByPrimaryKey", record);
    }

    /**
     * 获取对应cateId的品牌
     * @param cateId
     * @return
     */
    public List<GoBrand> getBrandByCateId (Integer cateId) {

        return getSqlSession().selectList("go_brand.getBrandByCateId",cateId);
    }

    /**
     * 获取所有的品牌数据
     * @param bounds
     * @return
     */
    public List<GoBrand> getAllBrandList (RowBounds bounds) {
        return getSqlSession().selectList("go_brand.getAllBrandList",null,bounds);
    }

    /**
     * 分页用
     * @return
     */
    public Integer getBrandListTotalNum() {
        return getSqlSession().selectOne("go_brand.getBrandListTotalNum");
    }

}