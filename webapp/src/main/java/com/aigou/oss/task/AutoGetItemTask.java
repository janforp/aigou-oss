package com.aigou.oss.task;

import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.service.ItemService;
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
@TaskCfg(cron = "0 0/25 5-23 * * ?", concurrent = true, runInit = true, threadCount = 1)
@Transactional
public class AutoGetItemTask extends AbstractTask {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AutomaticPurchaseTask automaticPurchaseTask;

    @Override
    public void run() {

        ILogger logger = LogMgr.getLogger();

        try {

            if(automaticPurchaseTask.getQueueSize() == 0) {

                List<GoShoplist> randomList = itemService.getRandomList();

                if(randomList != null) {

                    for (GoShoplist shop : randomList) {
                        automaticPurchaseTask.addReport(shop);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("AutoGetItemTask.run", e);
        }
    }
}
