package com.aigou.oss.dao;

import com.aigou.oss.model.GoMemberAddmoneyRecord;

import com.aigou.oss.model.vo.DayStatisticsVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by com.aigou.oss.MybatisCodeGenerate on 2016-06-27
 */
@Repository
public class GoMemberAddmoneyRecordDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(Integer id) {
        GoMemberAddmoneyRecord _key = new GoMemberAddmoneyRecord();
        _key.setId(id);
        return getSqlSession().delete("go_member_addmoney_record.deleteByPrimaryKey", _key);
    }

    public void insert(GoMemberAddmoneyRecord record) {
        getSqlSession().insert("go_member_addmoney_record.insert", record);
    }

    public void insertSelective(GoMemberAddmoneyRecord record) {
        getSqlSession().insert("go_member_addmoney_record.insertSelective", record);
    }

    public void insertBatch(List<GoMemberAddmoneyRecord> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("go_member_addmoney_record.insertBatch", records);
    }

    public GoMemberAddmoneyRecord selectByPrimaryKey(Integer id) {
        GoMemberAddmoneyRecord _key = new GoMemberAddmoneyRecord();
        _key.setId(id);
        return getSqlSession().selectOne("go_member_addmoney_record.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(GoMemberAddmoneyRecord record) {
        return getSqlSession().update("go_member_addmoney_record.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(GoMemberAddmoneyRecord record) {
        return getSqlSession().update("go_member_addmoney_record.updateByPrimaryKey", record);
    }


    /**
     *
     * @return
     */
    public BigDecimal getTotalRechargeMoneyTotay (){

        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数

        int startTime = (int) (zero/1000) ;
        int endTime = (int) (twelve/1000) ;

        Map<String,Object> params = new HashMap<>(2);
        params.put("startTime",startTime);
        params.put("endTime",endTime);

        return getSqlSession().selectOne("go_member_addmoney_record.getTotalRechargeMoneyTotay",params);
    }


    /**
     * 查询到当前时间的 总充值
     * @return
     */
    public BigDecimal selectTotalMoneyUtilNow() {

        return getSqlSession().selectOne("go_member_addmoney_record.selectTotalMoneyUtilNow");
    }



}