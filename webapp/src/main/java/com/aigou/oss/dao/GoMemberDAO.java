package com.aigou.oss.dao;

import com.aigou.oss.model.GoMember;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-13
 */
@Repository
public class GoMemberDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer uid) {
        GoMember _key = new GoMember();
        _key.setUid(uid);
        return getSqlSession().delete("go_member.deleteByPrimaryKey", _key);
    }

    public void insert(GoMember record) {
        getSqlSession().insert("go_member.insert", record);
    }

    public void insertSelective(GoMember record) {
        getSqlSession().insert("go_member.insertSelective", record);
    }

    public void insertBatch(List<GoMember> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_member.insertBatch", records);
    }

    public GoMember selectByPrimaryKey(Integer uid) {
        GoMember _key = new GoMember();
        _key.setUid(uid);
        return getSqlSession().selectOne("go_member.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoMember record) {
        return getSqlSession().update("go_member.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoMember record) {
        return getSqlSession().update("go_member.updateByPrimaryKey", record);
    }

    /**
     * 修改用户信息
     * @param userId
     * @param userName
     * @param userPhoto
     * @return
     */
    public int updateUser(Integer userId, String userName, String userPhoto) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("userPhoto", userPhoto);
        return getSqlSession().update("go_member.updateUser", map);
    }

    /**
     * 查询模块 按条件查询用户
     *
     * @param paramKey      条件
     * @param paramValue    输入值
     * @param bounds        分页
     * @return
     */
    public List<GoMember> findUsersByParamKeyAndParamValue (String paramKey,String paramValue,RowBounds bounds) {

        Map<String ,Object> params = new HashMap<>(2);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);

        return getSqlSession().selectList("go_member.findUsersByParamKeyAndParamValue",params,bounds);
    }

    /**
     * 查询模块 按条件查询用户
     *
     * @param paramKey      条件
     * @param paramValue    输入值
     * @return
     */
    public int findUsersNumByParamKeyAndParamValue (String paramKey,String paramValue) {

        Map<String ,Object> params = new HashMap<>(2);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);

        return getSqlSession().selectOne("go_member.findUsersNumByParamKeyAndParamValue",params);
    }

    /**
     *
     * @return
     */
    public int findTotalRegisterUserToday (){

        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数

        int startTime = (int) (zero/1000) ;
        int endTime = (int) (twelve/1000) ;

        Map<String,Object> params = new HashMap<>(2);
        params.put("startTime",startTime);
        params.put("endTime",endTime);

        return getSqlSession().selectOne("go_member.findTotalRegisterUserToday",params);
    }

    /**
     * 查询到当前时间的 总用户数量
     * @return
     */
    public Integer selectTotalUserUtilNow() {

        return getSqlSession().selectOne("go_member.selectTotalUserUtilNow");
    }



}