package com.aigou.oss.service;

import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.AgChannelRelationDAO;
import com.aigou.oss.dao.AgChannelStatisticsDAO;
import com.aigou.oss.dao.AgUserChannelDAO;
import com.aigou.oss.dao.AgUserRechargeChannelDAO;
import com.aigou.oss.model.AgChannelRelation;
import com.aigou.oss.model.AgChannelStatistics;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Summer on 16/6/16.
 */
@Service
public class ChannelService {

    @Autowired
    private AgChannelRelationDAO agChannelRelationDAO;

    @Autowired
    private AgChannelStatisticsDAO agChannelStatisticsDAO;

    @Autowired
    private AgUserChannelDAO agUserChannelDAO;

    @Autowired
    private AgUserRechargeChannelDAO agUserRechargeChannelDAO;

    /**
     * 查询所有渠道关系
     * @return
     */
    public List<AgChannelRelation> selectAllRelation() {
        return agChannelRelationDAO.selectAllRelation();
    }

    /**
     * 查询渠道关系
     * @param relationName
     * @return
     */
    public AgChannelRelation selectRelation(String relationName) {
        return agChannelRelationDAO.selectRelation(relationName);
    }

    /**
     * 查询总记录数
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public Integer selectCountByRelation(String channelName, String packageName, String today) {
        return agChannelStatisticsDAO.selectCountByRelation(channelName, packageName, today);
    }


    /**
     * 查询历史记录
     * @param channelName
     * @param packageName
     * @param today
     * @param pageNum
     * @return
     */
    public List<AgChannelStatistics> selectHistoryByRelation(String channelName, String packageName, String today, int pageNum) {
        int offSet = (pageNum - 1) * ValueConsts.CHANNEL_PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.CHANNEL_PAGE_SIZE);
        return agChannelStatisticsDAO.selectHistoryByRelation(channelName, packageName, today, bounds);
    }

    /**
     * 查询当日新增用户数
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public Integer selectPeopleCountByRelation(String channelName, String packageName, String today) {
        return agUserChannelDAO.selectPeopleCountByRelation(channelName, packageName, today);
    }

    /**
     * 查询当日充值总金额
     * @param channelName
     * @param packageName
     * @param today
     * @return
     */
    public BigDecimal selectRechargeCountByRelation(String channelName, String packageName, String today) {
        return agUserRechargeChannelDAO.selectRechargeCountByRelation(channelName, packageName, today);
    }

    /**
     * 记录今日数据
     * @param channelName
     * @param packageName
     * @param today
     * @param people
     * @param recharge
     */
    public void updateToday(String channelName, String packageName, String today, Integer people, BigDecimal recharge) {

        // 查询今天数据是否存在
        AgChannelStatistics statistics = agChannelStatisticsDAO.selectToday(channelName, packageName, today);

        if(statistics == null) { // 不存在 插入数据

            AgChannelStatistics record = new AgChannelStatistics();
            record.setDayRecharge(recharge);
            record.setDayPeople(people);
            record.setChannelName(channelName);
            record.setDayTime(today);
            record.setPackageName(packageName);
            agChannelStatisticsDAO.insert(record);

        }else { // 存在 更新数据

            statistics.setDayPeople(people);
            statistics.setDayRecharge(recharge);
            agChannelStatisticsDAO.updateByPrimaryKey(statistics);

        }

    }


}
