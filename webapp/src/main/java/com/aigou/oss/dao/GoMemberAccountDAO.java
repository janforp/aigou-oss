package com.aigou.oss.dao;

import com.aigou.oss.model.GoMemberAccount;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-22
 */
@Repository
public class GoMemberAccountDAO extends BaseSqlSessionDaoSupport {

    /**
     * 个人充值纪录 (先查出用户,再点击查询)
     * @param userId
     * @param bounds
     * @return
     */
    public List<GoMemberAccount> findAccountByUserId (Integer userId, RowBounds bounds) {
        return getSqlSession().selectList("go_member_account.findAccountByUserId",userId,bounds);
    }

    /**
     * 个人充值纪录 (先查出用户,再点击查询)
     * @param userId
     * @return
     * @return
     */
    public Integer findTotalAccountByUserId (Integer userId) {
        return getSqlSession().selectOne("go_member_account.findTotalAccountByUserId",userId);
    }


    /**
     * 充值纪录数量
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    public Integer findAccountNumByParamKeyParamValueTime ( String startTime,
                                                            String endTime,
                                                            String paramKey,
                                                            String paramValue) {

        Map<String,Object> params = new HashMap<>(4);
        params.put("endTime",endTime);
        params.put("startTime",startTime);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);

        return  getSqlSession().selectOne("go_member_account.findAccountNumByParamKeyParamValueTime",params);
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
    public List<GoMemberAccount> findAccountByParamKeyParamValueTime (String startTime,
                                                        String endTime,
                                                        String paramKey,
                                                        String paramValue,
                                                        RowBounds bounds) {

        Map<String,Object> params = new HashMap<>(4);
        params.put("endTime",endTime);
        params.put("startTime",startTime);
        params.put("paramKey",paramKey);
        params.put("paramValue",paramValue);

        return  getSqlSession().selectList("go_member_account.findAccountByParamKeyParamValueTime",params,bounds);
    }


}