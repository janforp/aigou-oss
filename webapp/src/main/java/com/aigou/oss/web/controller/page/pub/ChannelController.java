package com.aigou.oss.web.controller.page.pub;

import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.model.AgChannelRelation;
import com.aigou.oss.model.AgChannelStatistics;
import com.aigou.oss.service.ChannelService;
import com.aigou.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Summer on 16/6/12.
 */
@RequestMapping(value = "/p/c", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "/q/{relation_name}")
    public String test(HttpServletRequest request,
                       @PathVariable("relation_name") String relation_name) {

        AgChannelRelation relation = channelService.selectRelation(relation_name);

        if(relation == null) {
            return null;
        }

        String channelName = relation.getChannelName();
        String packageName = relation.getPackageName();
        String today = ElBase.fmtDay(System.currentTimeMillis());

        // 今日数据
        Integer people = channelService.selectPeopleCountByRelation(channelName, packageName, today);
        BigDecimal recharge = channelService.selectRechargeCountByRelation(channelName, packageName, today);

        if(recharge == null) {
            recharge = new BigDecimal(0);
        }

        AgChannelStatistics agChannelStatistics = new AgChannelStatistics();
        agChannelStatistics.setDayPeople(people);
        agChannelStatistics.setDayRecharge(recharge);

        // 记录今日数据
        channelService.updateToday(channelName, packageName, today, people, recharge);

        // 查询总记录数
        Integer total = channelService.selectCountByRelation(channelName, packageName, today);

        int totalPage = total / ValueConsts.CHANNEL_PAGE_SIZE;
        int remainder = total % ValueConsts.CHANNEL_PAGE_SIZE;
        if(remainder > 0) {
            totalPage = totalPage + 1;
        }

        // 历史数据
        List<AgChannelStatistics> list = channelService.selectHistoryByRelation(channelName, packageName, today, 1);

        request.setAttribute("channelName", channelName);
        request.setAttribute("packageName", packageName);
        request.setAttribute("today", agChannelStatistics);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("list", list);


        return "channel";

    }


}
