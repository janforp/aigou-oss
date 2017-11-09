package com.aigou.oss.dao;

import com.aigou.oss.model.AgBanner;
import com.aigou.oss.model.AgRecommendShop;
import com.aigou.oss.model.AgShopAllowGain;
import com.aigou.oss.model.GoShoplist;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
@Repository
public class GoShoplistDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoShoplist _key = new GoShoplist();
        _key.setId(id);
        return getSqlSession().delete("go_shoplist.deleteByPrimaryKey", _key);
    }

    public void insert(GoShoplist record) {
        getSqlSession().insert("go_shoplist.insert", record);
    }

    public void insertSelective(GoShoplist record) {
        getSqlSession().insert("go_shoplist.insertSelective", record);
    }

    public void insertBatch(List<GoShoplist> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_shoplist.insertBatch", records);
    }

    public GoShoplist selectByPrimaryKey(Integer id) {
        GoShoplist _key = new GoShoplist();
        _key.setId(id);
        return getSqlSession().selectOne("go_shoplist.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoShoplist record) {
        return getSqlSession().update("go_shoplist.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoShoplist record) {
        return getSqlSession().update("go_shoplist.updateByPrimaryKey", record);
    }

    /**
     * 查询晒单数量
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param paramKey 参数名
     * @param paramValue 参数值
     * @param status 状态
     * @return
     */
    public Integer selectUnreleaseCount(String startTime, String endTime, String paramKey, String paramValue, String status) {

        Map<String, Object> map = new HashMap<>(5);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("paramKey", paramKey);
        map.put("paramValue", paramValue);
        map.put("status", status);
        return getSqlSession().selectOne("go_shoplist.selectUnreleaseCount", map);

    }

    /**
     * 查询晒单列表
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param paramKey 参数名
     * @param paramValue 参数值
     * @param status 状态
     * @param rank 排序
     * @param bounds 分页参数
     * @return
     */
    public List<GoShoplist> selectUnreleaseOrder(String startTime, String endTime, String paramKey, String paramValue, String status, String rank, RowBounds bounds) {

        Map<String, Object> map = new HashMap<>(6);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("paramKey", paramKey);
        map.put("paramValue", paramValue);
        map.put("status", status);
        map.put("rank", rank);
        return getSqlSession().selectList("go_shoplist.selectUnreleaseOrder", map, bounds);

    }

    /**
     * 根据中奖用户的id,订单号查询此商品是否是虚拟商品,并返回商品id
     * @param userId
     * @return
     */
    public Integer selectVirtualShopId (Integer userId,String  orderCode){

        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("orderCode", orderCode);
        return getSqlSession().selectOne("go_shoplist.selectVirtualShopId",map);
    }




    /**
     * 中奖发货管理列表 商品
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param status        订单状态
     * @param paramValue    查询条件
     * @param paramKey      条件值
     * @param rank          排序规则
     * @param bounds        分页参数
     * @return
     */
    public List<GoShoplist> selectLotteryOrder(String startTime,String endTime,String status,String paramKey,String paramValue,String rank,RowBounds bounds){

        Map<String ,Object> params = new HashMap<>(6);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        params.put("status",status);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);
        params.put("rank",rank);
        return getSqlSession().selectList("go_shoplist.selectLotteryOrder",params,bounds);
    }

    /**
     * 中奖发货管理 查询数量
     * @param startTime
     * @param endTime
     * @param status
     * @param paramValue
     * @param paramKey
     * @return
     */
    public Integer selectLotteryOrderNum(String startTime,String endTime,String status,String paramValue,String paramKey){

        Map<String ,Object> params = new HashMap<>(5);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        params.put("status",status);
        params.put("paramValue",paramValue);
        params.put("paramKey",paramKey);
        return getSqlSession().selectOne("go_shoplist.selectLotteryOrderNum",params);
    }

    /**
     * 最新一期的商品
     *
     * @param sid 同一商品的id
     * @return
     */
    public GoShoplist selectLastShop(Integer sid) {
        return getSqlSession().selectOne("go_shoplist.selectLastShop", sid);
    }
    /**
     * 最新一期的商品
     *
     * @param sid 同一商品的id
     * @return
     */
    public GoShoplist selectLastShopLock(Integer sid) {
        return getSqlSession().selectOne("go_shoplist.selectLastShopLock", sid);
    }

    /**
     * 查询指定中奖的商品列表
     * @return
     */
    public List<GoShoplist> selectAssignList() {
        return getSqlSession().selectList("go_shoplist.selectAssignList");
    }

    /**
     * 随机获取商品
     * @param itemNum
     * @return
     */
    public List<GoShoplist> selectRandomList(Integer itemNum) {
        return getSqlSession().selectList("go_shoplist.selectRandomList", itemNum);
    }

    /**
     * 获得推荐商品,用于添加推荐商品
     * @return
     */
    public List<AgRecommendShop> getRecommendShopInShopList () {
        return getSqlSession().selectList("go_shoplist.getRecommendShopInShopList");
    }

    /**
     * 获得允许中奖商品,用于添加允许中奖商品
     * @return
     */
    public List<AgShopAllowGain> getAllowShopInShopList () {
        return getSqlSession().selectList("go_shoplist.getAllowShopInShopList");
    }

    /**
     * 获取到还不在banner中的商品
     * @return
     */
    public List<AgBanner> getUnBanneredShops(){

        return getSqlSession().selectList("go_shoplist.getUnBanneredShops");
    }

    /**
     * 修改的时候,需要所有的商品,包括已经在banner中的
     * @return
     */
    public List<AgBanner> getAllShop () {
        return getSqlSession().selectList("go_shoplist.getAllShop");
    }

    /**
     * 还没有开奖的 商品列表
     * @param bounds
     * @return
     */
    public List<GoShoplist> getGoodsListByStatus (Integer status,String title,RowBounds bounds) {

        Map<String,Object> map = new HashMap<>(2);
        map.put("status",status);
        map.put("title",title);

        return getSqlSession().selectList("go_shoplist.getGoodsListByStatus",map,bounds);
    }

    /**
     * 没有开奖的 商品列表 的总数 分页
     * @return
     */
    public Integer getGoodsListByStatusTotalNum (Integer status,String title){

        Map<String,Object> map = new HashMap<>(2);
        map.put("status",status);
        map.put("title",title);

        return getSqlSession().selectOne("go_shoplist.getGoodsListByStatusTotalNum",map);
    }

    /**
     * 修改的时候,枷锁
     * @param shopId
     * @return
     */
    public GoShoplist selectShopByIdLock(Integer shopId){

        return getSqlSession().selectOne("go_shoplist.selectShopByIdLock",shopId);
    }

    /**
     * 定时检测 云购码 卖完,但是 商品剩余次数 还有
     * @return
     */
    public List<Integer>  checkGoShopListCode () {

        return getSqlSession().selectList("go_shoplist.checkGoShopListCode");
    }
}