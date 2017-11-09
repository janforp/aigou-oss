package com.aigou.oss.task;

import com.aigou.oss.model.AgChannelRelation;
import com.aigou.oss.model.AgChannelStatistics;
import com.aigou.oss.service.ChannelService;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.craigq.quartz.task.support.IDynamicTriggerTask;
import org.craigq.quartz.task.support.IQueueTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Summer on 16/6/16.
 */
@Component
@TaskCfg(cron = "0 3,7 0 * * ?", concurrent = true, runInit = true, threadCount = 3)
@Transactional
public class ChannelStatisticsTask extends AbstractTask implements IDynamicTriggerTask, IQueueTask<AgChannelRelation> {

    @Autowired
    private ChannelService channelService;

    public ChannelStatisticsTask() {
    }

    // 一次run方法执行，最多执行多少个
    private final int maxHandleCountInOneRound = 100;

    public void addReport(AgChannelRelation relation) {
        boolean isRunning = this.isRunning();
        if (!isRunning) {
            // Task已经暂停，拒绝操作
            throw new RuntimeException(this.getClass().getSimpleName() + " 已暂停，拒绝请求");
        }
        if (relation != null) {
            queue.add(relation);
        }
    }

    /**
     * 队列对象
     */
    private final static ConcurrentLinkedQueue<AgChannelRelation> queue = new ConcurrentLinkedQueue<AgChannelRelation>();

    @Override
    public long getJobCountRemaining() {
        return queue.isEmpty() ? 0 : 2;
    }

    @Override
    public Collection<AgChannelRelation> getQueue() {
        return null;
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }

    @Override
    public void run() {
        ILogger logger = LogMgr.getLogger();
        try {
            int handleCount = 0;
            while ((handleCount++ < maxHandleCountInOneRound) && (!queue.isEmpty())) {

                AgChannelRelation dto = queue.poll();

                if (dto != null) {

                    String packageName = dto.getPackageName();
                    String channelName = dto.getChannelName();

                    // 获取昨天日期 yyyy-MM-dd
                    Date day = new Date(new Date().getTime()-24*60*60*1000);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String yesterday = format.format(day);

                    // 昨日数据
                    Integer people = channelService.selectPeopleCountByRelation(channelName, packageName, yesterday);
                    BigDecimal recharge = channelService.selectRechargeCountByRelation(channelName, packageName, yesterday);

                    if(recharge == null) {
                        recharge = new BigDecimal(0);
                    }

                    AgChannelStatistics agChannelStatistics = new AgChannelStatistics();
                    agChannelStatistics.setDayPeople(people);
                    agChannelStatistics.setDayRecharge(recharge);

                    // 记录昨日数据
                    channelService.updateToday(channelName, packageName, yesterday, people, recharge);
                }
            }
        } catch (Exception e) {
            logger.error("ChannelStatisticsTask.run", e);
        }

    }

}
