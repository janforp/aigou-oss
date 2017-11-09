package com.aigou.oss.dao;

import com.aigou.oss.model.GoAppPay;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-07-01
 */
@Repository
public class GoAppPayDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoAppPay _key = new GoAppPay();
        _key.setId(id);
        return getSqlSession().delete("go_app_pay.deleteByPrimaryKey", _key);
    }

    public void insert(GoAppPay record) {
        getSqlSession().insert("go_app_pay.insert", record);
    }

    public void insertSelective(GoAppPay record) {
        getSqlSession().insert("go_app_pay.insertSelective", record);
    }

    public void insertBatch(List<GoAppPay> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_app_pay.insertBatch", records);
    }

    public GoAppPay selectByPrimaryKey(Integer id) {
        GoAppPay _key = new GoAppPay();
        _key.setId(id);
        return getSqlSession().selectOne("go_app_pay.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoAppPay record) {
        return getSqlSession().update("go_app_pay.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoAppPay record) {
        return getSqlSession().update("go_app_pay.updateByPrimaryKey", record);
    }


    /**
     * 获取所有支付 列表
     * @return
     */
    public List<GoAppPay> getAllPay () {
        return getSqlSession().selectList("go_app_pay.getAllPay");
    }
}