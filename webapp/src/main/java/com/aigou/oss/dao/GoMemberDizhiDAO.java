package com.aigou.oss.dao;

import com.aigou.oss.model.GoMemberDizhi;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-17
 */
@Repository
public class GoMemberDizhiDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoMemberDizhi _key = new GoMemberDizhi();
        _key.setId(id);
        return getSqlSession().delete("go_member_dizhi.deleteByPrimaryKey", _key);
    }

    public void insert(GoMemberDizhi record) {
        getSqlSession().insert("go_member_dizhi.insert", record);
    }

    public void insertSelective(GoMemberDizhi record) {
        getSqlSession().insert("go_member_dizhi.insertSelective", record);
    }

    public void insertBatch(List<GoMemberDizhi> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_member_dizhi.insertBatch", records);
    }

    public GoMemberDizhi selectByPrimaryKey(Integer id) {
        GoMemberDizhi _key = new GoMemberDizhi();
        _key.setId(id);
        return getSqlSession().selectOne("go_member_dizhi.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoMemberDizhi record) {
        return getSqlSession().update("go_member_dizhi.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoMemberDizhi record) {
        return getSqlSession().update("go_member_dizhi.updateByPrimaryKey", record);
    }

    /**
     * 查找用户的默认地址
     * @param userId
     * @return
     */
    public GoMemberDizhi selectAddressByUidAndDefault(Integer userId) {

        return getSqlSession().selectOne("go_member_dizhi.selectAddressByUidAndDefault",userId);

    }

    /**
     * find all addresses of the user
     * @param userId
     * @return
     */
    public List<GoMemberDizhi> selectAddressesByUid(Integer userId) {

        return getSqlSession().selectList("go_member_dizhi.selectAddressesByUid",userId);
    }
}