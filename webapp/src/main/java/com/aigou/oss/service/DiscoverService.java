package com.aigou.oss.service;

import com.aigou.oss.dao.AgDiscoverConfigDAO;
import com.aigou.oss.model.AgDiscoverConfig;
import com.aigou.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jan on 16/7/6.
 *
 * 发现
 */
@Service
public class DiscoverService {

    @Autowired
    private AgDiscoverConfigDAO agDiscoverConfigDAO;

    /**
     * 获取发现数据
     * @param request
     * @return
     */
    public List<AgDiscoverConfig> getAllDiscover (HttpServletRequest request) {

        return agDiscoverConfigDAO.getAllDiscover();
    }

    /**
     * 添加discover
     * @param request
     * @param discover
     * @return
     */
    public String addCover (HttpServletRequest request,AgDiscoverConfig discover) {

        Integer discoverId = agDiscoverConfigDAO.insert(discover);

        if (discoverId > 0) {
            return JsonUtil.buildSuccess();
        }
        return JsonUtil.buildError("添加失败");
    }

    /**
     * 修改discover
     * @param request
     * @param discover
     * @return
     */
    public String updateDiscover (HttpServletRequest request,AgDiscoverConfig discover) {

        AgDiscoverConfig adiscover = agDiscoverConfigDAO.selectByPrimaryKey(discover.getDiscoverId());
        if (adiscover == null) {
            return JsonUtil.buildError("商品不存在");
        }
        Integer row = agDiscoverConfigDAO.updateByPrimaryKeySelective(discover);
        if (row==null ||row==0){
            return JsonUtil.buildError("修改失败");
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 删除多个banner纪录
     * @param request
     * @param bannerIds
     * @return
     */
    public String deleteDiscovers (HttpServletRequest request,String bannerIds) {

        String[] ids = bannerIds.split(",");
        for (String id : ids) {

            Integer bannerId = Integer.parseInt(id);
            agDiscoverConfigDAO.deleteByPrimaryKey(bannerId);
        }
        return JsonUtil.buildSuccess();
    }

}
