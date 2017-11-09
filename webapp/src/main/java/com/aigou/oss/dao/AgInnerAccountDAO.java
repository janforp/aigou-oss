package com.aigou.oss.dao;

import com.aigou.oss.model.AgInnerAccount;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-21
 */
@Repository
public class AgInnerAccountDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer userId) {
        AgInnerAccount _key = new AgInnerAccount();
        _key.setUserId(userId);
        return getSqlSession().delete("ag_inner_account.deleteByPrimaryKey", _key);
    }

    public void insert(AgInnerAccount record) {
        getSqlSession().insert("ag_inner_account.insert", record);
    }

    public void insertSelective(AgInnerAccount record) {
        getSqlSession().insert("ag_inner_account.insertSelective", record);
    }

    public void insertBatch(List<AgInnerAccount> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("ag_inner_account.insertBatch", records);
    }

    public AgInnerAccount selectByPrimaryKey(Integer userId) {
        AgInnerAccount _key = new AgInnerAccount();
        _key.setUserId(userId);
        return getSqlSession().selectOne("ag_inner_account.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(AgInnerAccount record) {
        return getSqlSession().update("ag_inner_account.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(AgInnerAccount record) {
        return getSqlSession().update("ag_inner_account.updateByPrimaryKey", record);
    }

    /**
     * 随机查询一个区间内用户
     * @param minId
     * @param maxId
     * @return
     */
    public int selectUserIdByRegion(Integer minId, Integer maxId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("minId", minId);
        map.put("maxId", maxId);
        return getSqlSession().selectOne("ag_inner_account.selectUserIdByRegion", map);
    }

    /**
     * 随机查询用户
     * @param userNum
     * @return
     */
    public List<Integer> selectUserIdByLimit(Integer userNum) {
        return getSqlSession().selectList("ag_inner_account.selectUserIdByLimit", userNum);
    }

    /**
     * 随机查询未购买过的用户
     * @param shopid
     * @param userNum
     * @return
     */
    public List<Integer> selectUnPurchase(Integer shopid, Integer userNum) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopid", shopid);
        map.put("userNum", userNum);
        return getSqlSession().selectList("ag_inner_account.selectUnPurchase", map);
    }

    /**
     * 随机查询1个用户
     * @return
     */
    public int selectUserIdRandom() {
        return getSqlSession().selectOne("ag_inner_account.selectUserIdRandom");
    }

    /**
     * 判断是否是内部账户
     * @param userId
     * @return
     */
    public Integer selectIsInnerAccount(Integer userId) {
        return getSqlSession().selectOne("ag_inner_account.selectIsInnerAccount", userId);
    }

}