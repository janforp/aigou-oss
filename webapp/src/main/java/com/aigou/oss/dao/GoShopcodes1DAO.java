package com.aigou.oss.dao;

import com.aigou.oss.model.GoShopcodes1;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-22
 */
@Repository
public class GoShopcodes1DAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoShopcodes1 _key = new GoShopcodes1();
        _key.setId(id);
        return getSqlSession().delete("go_shopcodes_1.deleteByPrimaryKey", _key);
    }

    public void insert(GoShopcodes1 record) {
        getSqlSession().insert("go_shopcodes_1.insert", record);
    }

    public void insertSelective(GoShopcodes1 record) {
        getSqlSession().insert("go_shopcodes_1.insertSelective", record);
    }

    public void insertBatch(List<GoShopcodes1> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_shopcodes_1.insertBatch", records);
    }

    public GoShopcodes1 selectByPrimaryKey(Integer id) {
        GoShopcodes1 _key = new GoShopcodes1();
        _key.setId(id);
        return getSqlSession().selectOne("go_shopcodes_1.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoShopcodes1 record) {
        return getSqlSession().update("go_shopcodes_1.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoShopcodes1 record) {
        return getSqlSession().update("go_shopcodes_1.updateByPrimaryKey", record);
    }

    /**
     * 查询云购码
     *
     * @param sid 商品id
     * @return
     */
    public GoShopcodes1 selectShopcodesBySid(Integer sid) {
        return getSqlSession().selectOne("go_shopcodes_1.selectShopcodesBySid", sid);
    }

    /**
     * 查询云购码
     *
     * @param sid 商品id
     * @return
     */
    public List<GoShopcodes1> selectShopcodesListBySid(Integer sid) {
        return getSqlSession().selectList("go_shopcodes_1.selectShopcodesListBySid", sid);
    }

    /**
     * 查询云购码
     *
     * @param sid 商品id
     * @param scid 序号
     * @return
     */
    public GoShopcodes1 selectShopcodesBySidAndScid(Integer sid, Integer scid) {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("sid", sid);
        map.put("scid", scid);
        return getSqlSession().selectOne("go_shopcodes_1.selectShopcodesBySidAndScid", map);
    }

    /**
     * 查询云购码剩余个数
     *
     * @param sid
     * @return
     */
    public Integer selectSumSlen(Integer sid) {
        return getSqlSession().selectOne("go_shopcodes_1.selectSumSlen", sid);
    }


}