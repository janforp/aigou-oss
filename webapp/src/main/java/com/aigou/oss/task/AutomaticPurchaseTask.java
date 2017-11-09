package com.aigou.oss.task;

import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.service.PurchaseService;
import com.aigou.oss.util.RandomUtil;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.craigq.quartz.task.support.IDynamicTriggerTask;
import org.craigq.quartz.task.support.IQueueTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Summer on 16/6/16.
 */
@Component
@TaskCfg(cron = "0/5 * * * * ?", concurrent = true, runInit = true, threadCount = 10)
@Transactional
public class AutomaticPurchaseTask extends AbstractTask implements IDynamicTriggerTask, IQueueTask<GoShoplist> {

    @Autowired
    private PurchaseService purchaseService;

    public AutomaticPurchaseTask() {
    }

    // 一次run方法执行，最多执行多少个
    private final int maxHandleCountInOneRound = 100;

    public void addReport(GoShoplist shop) {
        boolean isRunning = this.isRunning();
        if (!isRunning) {
            // Task已经暂停，拒绝操作
            throw new RuntimeException(this.getClass().getSimpleName() + " 已暂停，拒绝请求");
        }
        if (shop != null) {
            queue.add(shop);
        }
    }

    /**
     * 队列对象
     */
    private final static ConcurrentLinkedQueue<GoShoplist> queue = new ConcurrentLinkedQueue<GoShoplist>();

    @Override
    public long getJobCountRemaining() {
        return queue.isEmpty() ? 0 : 2;
    }

    @Override
    public Collection<GoShoplist> getQueue() {
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
                Thread.sleep(1000);
                GoShoplist shop = queue.poll();
                purchaseService.Purchase(shop);
            }
        } catch (Exception e) {
            logger.error("AutomaticPurchaseTask.run", e);
        }

    }

}
