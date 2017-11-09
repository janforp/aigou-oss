package com.aigou.oss.dao;

import com.aigou.oss.model.GoMemberGoRecord;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
@Repository
public class GoMemberGoRecordDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoMemberGoRecord _key = new GoMemberGoRecord();
        _key.setId(id);
        return getSqlSession().delete("go_member_go_record.deleteByPrimaryKey", _key);
    }

    public void insert(GoMemberGoRecord record) {
        getSqlSession().insert("go_member_go_record.insert", record);
    }

    public void insertSelective(GoMemberGoRecord record) {
        getSqlSession().insert("go_member_go_record.insertSelective", record);
    }

    public void insertBatch(List<GoMemberGoRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_member_go_record.insertBatch", records);
    }

    public GoMemberGoRecord selectByPrimaryKey(Integer id) {
        GoMemberGoRecord _key = new GoMemberGoRecord();
        _key.setId(id);
        return getSqlSession().selectOne("go_member_go_record.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoMemberGoRecord record) {
        return getSqlSession().update("go_member_go_record.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoMemberGoRecord record) {
        return getSqlSession().update("go_member_go_record.updateByPrimaryKey", record);
    }

    /**
     * 查询中奖订单
     *
     * @param shopId 商品id
     * @param huode 中奖码
     * @return
     */
    public GoMemberGoRecord selectRecordByShopIdAndHuode(Integer shopId, String huode) {
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        map.put("huode", huode);
        return getSqlSession().selectOne("go_member_go_record.selectRecordByShopIdAndHuode", map);
    }

    /**
     * 查看该订单号对应的纪录
     * @param userId
     * @param shopId
     * @param orderCode     订单号
     * @return
     */
    public GoMemberGoRecord selectRecordByUserIdAndShopIdAndOrderCode(Integer userId,Integer shopId,String orderCode) {

        Map<String,Object> params = new HashMap<>(3);
        params.put("userId",userId);
        params.put("shopId",shopId);
        params.put("orderCode",orderCode);

        return getSqlSession().selectOne("go_member_go_record.selectRecordByUserIdAndShopIdAndOrderCode",params);
    }


    /**
     * 更新订单状态(待收货-->已完成,或 待收货-->已作废)
     * @param orderStatus   更新之后的订单状态
     * @param userId
     * @param shopId
     * @param orderCode     订单号
     * @param lotteryCode   中奖码
     * @return
     */
    public int updateOrderStatusByOrderCodeUserIdShopIdOrderCodelotteryCode(Integer  orderStatus,
                                                                            Integer userId,
                                                                            Integer shopId,
                                                                            String orderCode,
                                                                            String lotteryCode) {
        Map<String ,Object> params = new HashMap<>(5);
        params.put("orderStatus",orderStatus);
        params.put("userId",userId);
        params.put("shopId",shopId);
        params.put("orderCode",orderCode);
        params.put("lotteryCode",lotteryCode);

        return getSqlSession().update("go_member_go_record.updateOrderStatusByOrderCodeUserIdShopIdOrderCodelotteryCode",params);

    }


    /**
     * 查询购买纪录
     *
     * @param shopid 商品id
     * @param nper 期数
     * @param scopeBegin 开始
     * @param scopeEnd 结束
     * @return
     */
    public GoMemberGoRecord selectLotteryRecord(Integer shopid, Integer nper, Integer scopeBegin, Integer scopeEnd) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("shopid", shopid);
        map.put("nper", nper);
        map.put("scopeBegin", scopeBegin);
        map.put("scopeEnd", scopeEnd);
        return getSqlSession().selectOne("go_member_go_record.selectLotteryRecord", map);
    }

    /**
     * 查询是否购买过
     * @param shopid 商品id
     * @return
     */
    public GoMemberGoRecord selectRecordByShopId(Integer shopid) {
        return getSqlSession().selectOne("go_member_go_record.selectRecordByShopId", shopid);
    }

    /**
     * 查询某个时间点之前的n条记录
     *
     * @param time 时间
     * @param pageSize 查询条数
     * @return
     */
    public List<GoMemberGoRecord> selectRecordByTime(String time, Integer pageSize) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("time", time);
        map.put("pageSize", pageSize);
        return getSqlSession().selectList("go_member_go_record.selectRecordByTime", map);

    }

    /**
     * 查询中奖码记录
     *
     * @param shopid 商品id
     * @param nper 期数
     * @param code 中奖码
     * @return
     */
    public GoMemberGoRecord selectLotteryRecordByCode(Integer shopid, Integer nper, String code) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("shopid", shopid);
        map.put("nper", nper);
        map.put("code", code);
        return getSqlSession().selectOne("go_member_go_record.selectLotteryRecordByCode", map);
    }

    /**
     * 查询最后一条购买记录
     *
     * @param shopid 商品id
     * @param nper 期数
     * @return
     */
    public GoMemberGoRecord selectRecordByShopidAndQishu(Integer shopid, Integer nper) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopid", shopid);
        map.put("nper", nper);
        return getSqlSession().selectOne("go_member_go_record.selectRecordByShopidAndQishu", map);
    }

    /**
     * 查询一条真实的用户购买记录
     * @param shopid
     * @param nper
     * @return
     */
    public GoMemberGoRecord selectRealRecord(Integer shopid, Integer nper) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopid", shopid);
        map.put("nper", nper);
        return getSqlSession().selectOne("go_member_go_record.selectRealRecord", map);
    }

    /**
     * 查询购买次数最多的用户记录
     * @param shopid
     * @param nper
     * @return
     */
    public GoMemberGoRecord selectMaximumRecord(Integer shopid, Integer nper) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopid", shopid);
        map.put("nper", nper);
        return getSqlSession().selectOne("go_member_go_record.selectMaximumRecord", map);
    }

    /**
     * 查询用户的购买纪录
     *
     * @param userId    用户ID
     * @param bounds    分页
     * @return
     */
    public List<GoMemberGoRecord> selectRecordByUid(Integer userId, RowBounds bounds) {

        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        return getSqlSession().selectList("go_member_go_record.selectRecordByUid",map,bounds);
    }

    /**
     * 查询用户的购买纪录 总数量
     *
     * @param userId    用户ID
     * @return
     */
    public Integer selectTotalRecordByUid(Integer userId) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);

        return getSqlSession().selectOne("go_member_go_record.selectTotalRecordByUid",map);
    }

    /**
     * 查询n条纪录
     * @param id
     * @param pageSize
     * @return
     */
    public List<GoMemberGoRecord> selectRecordById(Integer id, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        map.put("pageSize", pageSize);
        return getSqlSession().selectList("go_member_go_record.selectRecordById", map);

    }

    /**
     * 查询一条内部用户购买记录
     * @param shopid
     * @param nper
     * @return
     */
    public GoMemberGoRecord selectInnerRecord(Integer shopid, Integer nper) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopid", shopid);
        map.put("nper", nper);
        return getSqlSession().selectOne("go_member_go_record.selectInnerRecord", map);
    }



    /**
     * 充值纪录数量
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    public Integer findRecordNumByParamKeyParamValueTime ( String startTime,
                                                            String endTime,
                                                            String paramKey,
                                                            String paramValue) {

        Map<String,Object> params = new HashMap<>(4);
        params.put("endTime",endTime);
        params.put("startTime",startTime);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);

        return  getSqlSession().selectOne("go_member_go_record.findRecordNumByParamKeyParamValueTime",params);
    }


    /**
     * 充值查询  分页
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @param bounds        分页对象
     * @return
     */
    public List<GoMemberGoRecord> findRecordByParamKeyParamValueTime (String startTime,
                                                                      String endTime,
                                                                      String paramKey,
                                                                      String paramValue,
                                                                      RowBounds bounds) {

        Map<String,Object> params = new HashMap<>(4);
        params.put("endTime",endTime);
        params.put("startTime",startTime);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);

        return  getSqlSession().selectList("go_member_go_record.findRecordByParamKeyParamValueTime",params,bounds);
    }


    /**
     * 随机查询一条有资格中奖的用户购买记录
     *
     * @param shopid 商品id
     * @param nper 期数
     * @return
     */
    public GoMemberGoRecord selectAllowLotteryRecord(Integer shopid, Integer nper) {
        Map<String,Object> params = new HashMap<>(2);
        params.put("shopid", shopid);
        params.put("nper", nper);
        return  getSqlSession().selectOne("go_member_go_record.selectAllowLotteryRecord", params);
    }




    /**
     * 虚拟商品充值之后自动修改订单状态
     * @param userId    用户id
     * @param shopId    商品id
     * @param orderCode 订单号
     * @return
     */
    public Integer updateOrderCodeByUserIdShopIdOrderCode (Integer userId,Integer shopId,String orderCode){

        Map<String ,Object> params = new HashMap<>(3);
        params.put("userId",userId);
        params.put("shopId",shopId);
        params.put("orderCode",orderCode);


        return getSqlSession().update("go_member_go_record.updateOrderCodeByUserIdShopIdOrderCode",params);


    }
}