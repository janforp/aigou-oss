package com.aigou.oss.service;

import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.dao.GoShaidanDAO;
import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoMember;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.GoShaidan;
import com.aigou.oss.model.GoShoplist;
import com.aigou.oss.model.vo.OrderVo;
import com.aigou.oss.model.vo.ReleaseListVo;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import com.aigou.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Summer on 16/6/13.
 */
@Service
public class ShowOrderService {

    @Autowired
    private GoShoplistDAO goShoplistDAO;

    @Autowired
    private GoMemberDAO goMemberDAO;

    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;

    @Autowired
    private GoShaidanDAO goShaidanDAO;


    /**
     * 查询记录数量
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param paramKey 参数名
     * @param paramValue 参数值
     * @param status 状态    未晒单的
     * @return
     */
    public Integer getUnreleaseCount(String startTime, String endTime, String paramKey, String paramValue, String status) {

        Integer total = goShoplistDAO.selectUnreleaseCount(startTime, endTime, paramKey, paramValue, status);

        return total;
    }

    /**
     * 获取已经晒单的列表的数据的总数 分页
     * @param startTime
     * @param endTime
     * @param paramKey
     * @param paramValue
     * @param status
     * @return
     */
    public Integer getReleaseListCount(String startTime, String endTime, String paramKey, String paramValue, String status){

        return  goShaidanDAO.selectReleaseCount(startTime, endTime, paramKey, paramValue);
    }

    /**
     * 晒单纪录
     * @param startTime
     * @param endTime
     * @param rank
     * @param paramKey
     * @param paramValue
     * @param status
     * @param pageNum
     * @return
     */
    public List<ReleaseListVo> getReleaseList(String startTime, String endTime, String rank, String paramKey, String paramValue, String status, Integer pageNum) {

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);

        List<GoShaidan> list = goShaidanDAO.selectReleaseOrder(startTime, endTime, paramKey, paramValue, rank, bounds);

        List<ReleaseListVo> vos = new ArrayList<>(list.size());

        for (GoShaidan shaidan : list) {

            ReleaseListVo vo = turnGoShaidanToReleaseListVo (shaidan);

            vos.add(vo);
        }

        return vos;
    }

    /**
     * 实体类转换
     * @param shaidan
     * @return
     */
    public ReleaseListVo turnGoShaidanToReleaseListVo(GoShaidan shaidan) {

        ReleaseListVo vo = new ReleaseListVo();

        vo.setSdId(shaidan.getSdId());
        vo.setUserId(shaidan.getSdUserid());
        vo.setSdUserid(shaidan.getSdUserid());
        vo.setSdShopid(shaidan.getSdShopid());
        vo.setSdQishu(shaidan.getSdQishu());
        String sdTitle = shaidan.getSdTitle();
        if (StringUtil.isEmpty(sdTitle)) {
            sdTitle = "";
        }
        vo.setSdTitle(sdTitle);

        String sdThumbs = shaidan.getSdThumbs();
        if (StringUtil.isEmpty(sdThumbs)) {
            sdThumbs = "";
        }
        vo.setSdThumbs(sdThumbs);

        String content = shaidan.getSdContent();
        if (StringUtil.isEmpty(content)){
            content = "";
        }
        vo.setSdContent(content);

        String sdPhotolist = shaidan.getSdPhotolist();
        if (StringUtil.isEmpty(sdPhotolist)){
            sdPhotolist = "";
        }
        vo.setSdPhotolist(sdPhotolist);
        String[] pics = sdPhotolist.split(";");
        List<String> photos = new ArrayList<>(pics.length);
        photos = Arrays.asList(pics);
        vo.setPhotos(photos);

        Integer sdTime = shaidan.getSdTime();
        String sdTimeShow = ElBase.ftm10Time(sdTime);
        vo.setSdTime(sdTime);
        vo.setSdTimeShow(sdTimeShow);

        Integer shopId = shaidan.getSdShopid();

        String title = "";
        if (shopId != null) {
            GoShoplist shop = goShoplistDAO.selectByPrimaryKey(shopId);
            if (shop != null) {
                title = shop.getTitle();
            }
        }
        vo.setShopTitle(title);

        return vo;
    }


    /**
     * 查询记录
     *
     * @param startTime
     * @param endTime
     * @param rank
     * @param paramKey
     * @param paramValue
     * @param status
     * @param pageNum
     * @return
     */
    public List<OrderVo> getUnreleaseList(String startTime, String endTime, String rank, String paramKey, String paramValue, String status, Integer pageNum) {

        List<OrderVo> orderList = new ArrayList<>();

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);

        List<GoShoplist> list = goShoplistDAO.selectUnreleaseOrder(startTime, endTime, paramKey, paramValue, status, rank, bounds);

        if(list != null) {

            for (GoShoplist shop : list) {

                OrderVo order = new OrderVo();

                order.setQishu(shop.getQishu());
                order.setTitle(shop.getTitle());

                String q_end_time = shop.getQEndTime();
                order.setQ_end_time(ElBase.fmtTime(Long.valueOf(q_end_time.replace(".",""))));

                String q_user_code = shop.getQUserCode();
                order.setQ_user_code(q_user_code);

                Integer uid = shop.getQUid();
                order.setUserId(uid);

                GoMember member = goMemberDAO.selectByPrimaryKey(uid);
                if(member != null) {
                    order.setNickName(member.getUsername());
                }

                order.setStatus(status);

                Integer shopId = shop.getId();
                order.setShopId(shopId);

                GoMemberGoRecord record = goMemberGoRecordDAO.selectRecordByShopIdAndHuode(shopId, q_user_code);

                order.setOrderCode(record.getCode());

                orderList.add(order);

            }

        }

        return orderList;

    }

    /**
     * 查询晒单记录
     *
     * @param shopId
     * @param userId
     * @return
     */
    public GoShaidan selectRecordByShopIdAndUserId(Integer shopId, Integer userId) {

        return goShaidanDAO.selectRecordByShopIdAndUserId(shopId, userId);

    }

    /**
     * 晒单
     *
     * @param shopId
     * @param userId
     * @param title
     * @param content
     * @param releaseImg
     * @return
     */
    public String release(Integer shopId, Integer userId, String title, String content, String releaseImg) {

        GoShoplist shop = goShoplistDAO.selectByPrimaryKey(shopId);

        GoMember member = goMemberDAO.selectByPrimaryKey(userId);

        GoShaidan shaidan = new GoShaidan();
        shaidan.setSdUserid(userId);
        shaidan.setSdShopid(shopId);
        shaidan.setSdQishu(shop.getQishu());
        shaidan.setSdIp(member.getUserIp());
        shaidan.setSdTitle(title);
        shaidan.setSdContent(content);
        shaidan.setSdZhan(0);
        shaidan.setSdPing(0);
        shaidan.setSdShopsid(shop.getSid());

        Integer time = (int) (System.currentTimeMillis() / 1000);
        shaidan.setSdTime(time);

        String[] imgs = releaseImg.split(";");

        shaidan.setSdThumbs(imgs[0]);
        shaidan.setSdPhotolist(releaseImg);

        goShaidanDAO.insert(shaidan);

        return JsonUtil.buildSuccess();

    }

    /**
     * 修改晒单
     * @param shaidanId 晒单ID
     * @param shopId    商品ID
     * @param userId    用户ID
     * @param title
     * @param content
     * @param releaseImg
     * @return
     */
    public String doUpdateShaidan (Integer shaidanId,Integer shopId, Integer userId, String title, String content, String releaseImg) {

        GoShoplist shop = goShoplistDAO.selectByPrimaryKey(shopId);

        GoMember member = goMemberDAO.selectByPrimaryKey(userId);


        GoShaidan shaidan = goShaidanDAO.selectByPrimaryKey(shaidanId);

        shaidan.setSdUserid(userId);
        shaidan.setSdShopid(shopId);
        shaidan.setSdQishu(shop.getQishu());
        shaidan.setSdIp(member.getUserIp());
        shaidan.setSdTitle(title);
        shaidan.setSdContent(content);
        shaidan.setSdZhan(0);
        shaidan.setSdPing(0);
        shaidan.setSdShopsid(shop.getSid());

        String[] imgs = releaseImg.split(";");

        shaidan.setSdThumbs(imgs[0]);
        shaidan.setSdPhotolist(releaseImg);

        goShaidanDAO.updateByPrimaryKey(shaidan);

        return JsonUtil.buildSuccess();
    }

    /**
     * 删除晒单
     * @param request
     * @param sdIds
     * @return
     */
    public String doDeleteShaidan(HttpServletRequest request,String sdIds) {

        String[] ids = sdIds.split("&");
        List<String> sIds = Arrays.asList(ids);
        for (String id : sIds) {
            Integer intId = Integer.valueOf(id);

            GoShaidan shaidan = goShaidanDAO.selectByPrimaryKey(intId);
            if (shaidan != null) {
                goShaidanDAO.deleteByPrimaryKey(intId);
            }
        }
        return JsonUtil.buildSuccess("成功删除"+sIds.size()+"条");
    }
}
