package com.aigou.oss.task;

import com.aigou.oss.model.AgChannelRelation;
import com.aigou.oss.service.ChannelService;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Summer on 16/6/16.
 */
@Component
@TaskCfg(cron = "0 1,5 0 * * ?", concurrent = true, runInit = true, threadCount = 3)
@Transactional
public class ChannelRelationTask extends AbstractTask {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelStatisticsTask channelStatisticsTask;

    @Override
    public void run() {

        ILogger logger = LogMgr.getLogger();

        try {

            List<AgChannelRelation> relationList = channelService.selectAllRelation();
            if(relationList != null) {

                for (AgChannelRelation relation : relationList) {
                    channelStatisticsTask.addReport(relation);
                }

            }

        } catch (Exception e) {
            logger.error("ChannelStatisticsTask.run", e);
        }

    }

}
