package com.aigou.oss.service;

import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.AgUserChannelDAO;
import com.aigou.oss.dao.AgUserRechargeChannelDAO;
import com.aigou.oss.dao.GoMemberAddmoneyRecordDAO;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.model.vo.DayStatisticsVo;
import com.aigou.oss.model.vo.PackageChannelDetailVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/6/30.
 *  * 渠道统计 模块
 */
@Service
public class ChannelStatisticsService {

    @Autowired
    private GoMemberDAO goMemberDAO;
    @Autowired
    private GoMemberAddmoneyRecordDAO goMemberAddmoneyRecordDAO;
    @Autowired
    private AgUserRechargeChannelDAO agUserRechargeChannelDAO;
    @Autowired
    private AgUserChannelDAO agUserChannelDAO;

    /**
     * 每天的 统计(每天新增用户/新增用户充值/总充值)
     * @param request
     * @param pageNum   页码
     * @return
     */
    public List<DayStatisticsVo> doGetDayStatistics (HttpServletRequest request, Integer pageNum) {

        int offSet = (pageNum-1)* ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet,ValueConsts.PAGE_SIZE);

        List<DayStatisticsVo> list = agUserChannelDAO.selectDayStatisticsDayByDay (bounds);

        for (DayStatisticsVo vo : list) {

            //根据每个vo.time的日期,去ag_user_recharge_channel查新增用户的充值总额
            BigDecimal dayNewUserCharge = agUserRechargeChannelDAO.selectNewUserChargeDayByDay (vo.getTime());
            if (dayNewUserCharge == null) {
                dayNewUserCharge = new BigDecimal(0);
            }
            vo.setDayNewUserCharge(dayNewUserCharge);
            //查询每天的总充值额(老用户包括在内,但是都在ag_user_recharge_channel纪录了)
            BigDecimal dayTotalCharge = agUserRechargeChannelDAO.selectTotalUserChargeDayByDay(vo.getTime());
            if (dayTotalCharge == null) {
                dayTotalCharge = new BigDecimal(0);
            }
            vo.setDayTotalCharge(dayTotalCharge);
        }
        return list;
    }

    /**
     * 每天的 统计(每天新增用户/新增用户充值/总充值)的总的纪录数
     * @param request
     * @return
     */
    public Integer doGetDayStatisticsNums (HttpServletRequest request) {

        return  agUserChannelDAO.selectDayStatisticsDayByDayNums();

    }


    /**
     * 查询到当前时间的 总用户数量
     * @param request
     * @return
     */
    public Integer doGetTotalUserUtilNow (HttpServletRequest request) {

        return goMemberDAO.selectTotalUserUtilNow();

    }

    /**
     * 查询到当前时间的 总充值
     * @param request
     * @return
     */
    public BigDecimal doGetTotalMoneyUtilNow (HttpServletRequest request) {

        return goMemberAddmoneyRecordDAO.selectTotalMoneyUtilNow();

    }


    /**
     * 先查出一共有多少个  包+渠道 的组合
     * @param request
     * @return
     */
    public List<PackageChannelDetailVo> getPackageChannelCombination  (HttpServletRequest request) {

        return agUserChannelDAO.getAllPackageChannelCombination();
    }

    /**
     * 根据所有的 包+渠道的 组合,加上给的的 日期 ,查询具体的数据
     * @param request
     * @param packChannels
     * @return
     */
    public List<PackageChannelDetailVo> getPackageChannelDetailByDay(HttpServletRequest request,List<PackageChannelDetailVo> packChannels,String time){

        for (PackageChannelDetailVo vo : packChannels) {

            String packageName = vo.getPackageName();
            String channelName = vo.getChannelName();
            //当日新增用户数
            Integer dayNewUser = agUserChannelDAO.selectPeopleCountByRelation(channelName,packageName,time);
            //当日新增用户充值
            BigDecimal dayNewUserCharge = agUserRechargeChannelDAO.selectNewUserChargeByDayPackageChannel(channelName,packageName,time);
            if (dayNewUserCharge == null) {
                dayNewUserCharge = new BigDecimal(0) ;
            }
            //当日的总充值
            BigDecimal dayTotalCharge = agUserRechargeChannelDAO.selectTotalUserChargeByDayPackageChannel(channelName,packageName,time);
            if (dayTotalCharge == null) {
                dayTotalCharge = new BigDecimal(0);
            }
            vo.setDayNewUser(dayNewUser);
            vo.setDayNewUserCharge(dayNewUserCharge);
            vo.setDayTotalCharge(dayTotalCharge);
        }

        return  packChannels;
    }
}
