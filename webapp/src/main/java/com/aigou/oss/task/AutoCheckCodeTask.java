package com.aigou.oss.task;

import com.aigou.oss.dao.GoShopcodes1DAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.AgChannelRelation;
import com.aigou.oss.model.GoShopcodes1;
import com.aigou.oss.model.GoShoplist;
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
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Jan on 16/8/8.
 *
 * 定时检测 云购码 卖完,但是 商品剩余次数 还有
 */
@Component
@TaskCfg(cron = "0/5 * * * * ?", concurrent = true, runInit = true, threadCount = 1)
@Transactional
public class AutoCheckCodeTask extends AbstractTask implements IDynamicTriggerTask, IQueueTask<GoShoplist> {

    @Autowired
    private GoShoplistDAO goShoplistDAO;
    @Autowired
    private GoShopcodes1DAO goShopcodes1DAO;

    /**
     * 队列对象
     */
    private final static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

    public void addReport(Integer shopId) {

        boolean isRunning = this.isRunning();

        if (!isRunning) {
            // Task已经暂停，拒绝操作
            throw new RuntimeException(this.getClass().getSimpleName() + " 已暂停，拒绝请求");
        }
        if (shopId != null) {

            queue.add(shopId);
        }
    }

    @Override
    public void run() {

        ILogger logger = LogMgr.getLogger();

        try {

            while (! queue.isEmpty()) {

                Integer id = queue.poll();

                GoShoplist shop = goShoplistDAO.selectByPrimaryKey(id);

                if (shop.getShenyurenshu() != 0){

                    List<GoShopcodes1> codes = goShopcodes1DAO.selectShopcodesListBySid(id);

                    for (GoShopcodes1 code : codes) {

                        //该商品的所有 云购码  纪录都没有的时候
                        if (code.getSLen() != 0) {

                            return;
                        }
                    }

                    shop.setShenyurenshu(0);

                    shop.setCanyurenshu(shop.getZongrenshu());

                    goShoplistDAO.updateByPrimaryKey(shop);
                }
            }

        }catch (Exception e){
            logger.error("AutoCheckCodeTask.run", e);
        }
    }

    @Override
    public long getJobCountRemaining() {
        return 0;
    }

    @Override
    public Collection<GoShoplist> getQueue() {
        return null;
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }
}
