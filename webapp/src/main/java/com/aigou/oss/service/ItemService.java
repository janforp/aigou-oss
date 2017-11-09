package com.aigou.oss.service;

import com.aigou.oss.dao.AgParamConfigDAO;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.AgParamConfig;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/6/22.
 */
@Service
public class ItemService {

    @Autowired
    private GoShoplistDAO goShoplistDAO;

    @Autowired
    private AgParamConfigDAO agParamConfigDAO;

    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;

    /**
     * 查询指定中奖未购买的商品列表
     * @return
     */
    public List<GoShoplist> getAssignList() {

        List<GoShoplist> list = new ArrayList<>();

        List<GoShoplist> allList = goShoplistDAO.selectAssignList();

        if(allList != null) {
            for (GoShoplist shop : allList) {
                // 判断区间内用户是否购买过
                GoMemberGoRecord innerRecord = goMemberGoRecordDAO.selectLotteryRecord(shop.getId(), shop.getQishu(), shop.getQuyuBegin(), shop.getQuyuEnd());
                if(innerRecord == null) {
                    list.add(shop);
                }
            }
        }

        return list;
    }

    /**
     * 随机获取一些商品
     * @return
     */
    public List<GoShoplist> getRandomList() {
        // 随机商品数
        AgParamConfig itemConfig = agParamConfigDAO.selectByPrimaryKey("RANDOM_ITEM");
        Integer itemNum = RandomUtil.getRandomBetweenMinAndMax(Integer.valueOf(itemConfig.getFirstValue()), Integer.valueOf(itemConfig.getSecondValue()));
        return goShoplistDAO.selectRandomList(itemNum);

    }

}
