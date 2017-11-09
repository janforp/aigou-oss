package com.aigou.oss.task;

import com.aigou.oss.dao.GoShoplistDAO;
import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.craigq.quartz.annotation.TaskCfg;
import org.craigq.quartz.task.AbstractTask;
import org.craigq.quartz.task.support.IDynamicTriggerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jan on 16/8/8.
 *
 * 先查询 定时检测 云购码 卖完,但是 商品剩余次数 还有
 */
@Component
@TaskCfg(cron = "0 0/5 * * * ?", concurrent = true, runInit = true, threadCount = 1)
@Transactional
public class AutoGetCheckCodeTask extends AbstractTask implements IDynamicTriggerTask {
    @Autowired
    private GoShoplistDAO goShoplistDAO;
    @Autowired
    private AutoCheckCodeTask autoCheckCodeTask;

    @Override
    public void run() {

        ILogger logger = LogMgr.getLogger();

        try {

            List<Integer> ids = goShoplistDAO.checkGoShopListCode();

            for (Integer id : ids) {

                autoCheckCodeTask.addReport(id);
            }

        }catch (Exception e){
            logger.error("AutoGetCheckCodeTask.run", e);
        }
    }

    @Override
    public long getJobCountRemaining() {
        return 0;
    }
}
