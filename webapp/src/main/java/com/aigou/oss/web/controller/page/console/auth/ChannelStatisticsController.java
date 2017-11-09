package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.ParamConsts;
import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoMemberAddmoneyRecordDAO;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.model.vo.DayStatisticsVo;
import com.aigou.oss.model.vo.PackageChannelDetailVo;
import com.aigou.oss.service.ChannelStatisticsService;
import com.aigou.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/6/27.
 * 渠道统计 模块
 */
@Controller
@RequestMapping(value = "/page/console/auth/statistics",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class ChannelStatisticsController {

    @Autowired
    private ChannelStatisticsService channelStatisticsService;

    /**
     * 渠道统计(每天的用户/充值/总充值统计)
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/moneyAndUser")
    public String  getDayStatistics (HttpServletRequest request,
                                     @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum){

        //查询总用户数量
        Integer totalUser = channelStatisticsService.doGetTotalUserUtilNow(request);
        //查询总充值量
        BigDecimal totalMoney = channelStatisticsService.doGetTotalMoneyUtilNow(request);
        //查询DaystatisticsVo列表
        List<DayStatisticsVo> list = channelStatisticsService.doGetDayStatistics(request,pageNum);
        //总日期数
        Integer total = channelStatisticsService.doGetDayStatisticsNums(request);
        int totalPage = 1;
        if (total % ValueConsts.PAGE_SIZE == 0) {
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE +1 ;
        }

        request.setAttribute("pageNow",pageNum);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("totalUser",totalUser);
        request.setAttribute("totalMoney",totalMoney);
        request.setAttribute("list",list);

        return  "channel_statistics";
    }


    /**
     * 渠道统计(每天的用户/充值/总充值统计) 翻页
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/pageBeforeAndAfter")
    @ResponseBody
    public String  getDayStatisticsByPage (HttpServletRequest request,
                                     @RequestParam(value = ParamConsts.pageNum,defaultValue = "1") Integer pageNum){

        List<DayStatisticsVo> list = channelStatisticsService.doGetDayStatistics(request,pageNum);

        Map<String ,Object> map = new HashMap<>(1);

        map.put("list", list);

        return JsonUtil.buildData(map);
    }

    /**
     * 根据选定的日期,查询   包+渠道+日期  的新增,充值,总充值
     * @param request
     * @param time      具体某日期(如: 2016-6-30)
     * @return
     */
    @RequestMapping(value = "/packageAndChannel")
    public String seePackageAndChannelDetail (HttpServletRequest request,
                                              @RequestParam(value = ParamConsts.time,required = true) String time) {

        //1.先查出所有  包+渠道 的组合
        List<PackageChannelDetailVo> packChannels = channelStatisticsService.getPackageChannelCombination (request);
        //2.再根据不同的 包+渠道+日期  查询每日的数据
        List<PackageChannelDetailVo> list = channelStatisticsService.getPackageChannelDetailByDay (request,packChannels,time) ;


        //查询总用户数量
        Integer totalUser = channelStatisticsService.doGetTotalUserUtilNow(request);
        //查询总充值量
        BigDecimal totalMoney = channelStatisticsService.doGetTotalMoneyUtilNow(request);

        request.setAttribute("list",list);
        request.setAttribute("time",time);

        request.setAttribute("totalUser",totalUser);
        request.setAttribute("totalMoney",totalMoney);

        return "package_channel_detail";
    }

}
