package com.aigou.oss.service;

import com.aigou.oss.consts.ValueConsts;
import com.aigou.oss.dao.GoMemberAccountDAO;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.dao.GoMemberGoRecordDAO;
import com.aigou.oss.model.GoMember;
import com.aigou.oss.model.GoMemberAccount;
import com.aigou.oss.model.GoMemberGoRecord;
import com.aigou.oss.model.vo.ShowAccountVo;
import com.aigou.oss.model.vo.ShowRecordVo;
import com.aigou.oss.model.vo.UserInfo;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.el.ElBase;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/6/23.
 *
 * 查找模块
 */
@Service
public class BuyAndRecharegeService {


    @Autowired
    private GoMemberDAO goMemberDAO;
    @Autowired
    private GoMemberGoRecordDAO goMemberGoRecordDAO;
    @Autowired
    private GoMemberAccountDAO goMemberAccountDAO;

    /**
     * 点击用户查询 按钮
     * @param request
     * @param paramKey      查询条件
     * @param paramValue    输入值
     * @param pageNum       页码
     * @return
     */
    public List<UserInfo> doFindUser (HttpServletRequest request,
                                      String paramKey,
                                      String paramValue,
                                      Integer pageNum) {

        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet,ValueConsts.PAGE_SIZE);
        //排除机器人
        List<GoMember> members = goMemberDAO.findUsersByParamKeyAndParamValue (paramKey,paramValue,bounds);

        List<UserInfo> users = null;

        if (members != null && members.size() != 0) {

            users = new ArrayList<>(members.size());

            for (GoMember member : members) {

                UserInfo user = new UserInfo();

                user.setUserId(member.getUid());

                String userName = member.getUsername();
                if(userName == null) {
                    userName = "";
                }
                user.setUserName(userName);

                String phone = member.getMobile();
                if (phone == null) {
                    phone = "";
                }
                user.setPhone(phone);
                user.setMoney(member.getMoney());

                users.add(user);
            }
        }
        return users;
    }

    /**
     * 点击用户查询 按钮 查询满足条件的用户数量
     * @param request
     * @param paramKey      查询条件
     * @param paramValue    输入值
     * @return
     */
    public int doFindUserNum (HttpServletRequest request,
                              String paramKey,
                              String paramValue) {

        return goMemberDAO.findUsersNumByParamKeyAndParamValue(paramKey,paramValue);
    }


    /**
     * 返回 查询 用户列表
     * @param request
     * @param paramKey
     * @param paramValue
     * @param pageNum
     * @return
     */
    public String listUsers (HttpServletRequest request,
                             String paramKey,
                             String paramValue,
                             Integer pageNum) {

        List<UserInfo> list = doFindUser(request,paramKey,paramValue,pageNum);

        Integer total = doFindUserNum(request,paramKey,paramValue);

        Integer totalPage = 1;
        if(total % ValueConsts.PAGE_SIZE == 0){
            totalPage = total / ValueConsts.PAGE_SIZE;
        }else {
            totalPage = total / ValueConsts.PAGE_SIZE + 1;
        }

        if(list == null || list.size() == 0) {
            return JsonUtil.buildError("没有满足条件的用户");
        }

        Map<String ,Object> map = new HashMap<>(2);
        map.put("totalPage", totalPage);

        map.put("list", list);

        return JsonUtil.buildData(map);
    }

    /**
     * 查询用户的 购买纪录
     *
     * 分页
     *
     * @param request
     * @param userId
     * @return
     */
    public List<ShowRecordVo> getShowRecords (HttpServletRequest request,Integer userId,Integer pageNum) {

        int offSet = (pageNum-1)* ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet,ValueConsts.PAGE_SIZE);

        List<GoMemberGoRecord> records = goMemberGoRecordDAO.selectRecordByUid(userId,bounds);

        List<ShowRecordVo> showRecordVos = new ArrayList<>(records.size());

        if (records != null && records.size() != 0) {

            for (GoMemberGoRecord record : records) {

                ShowRecordVo show = new ShowRecordVo();
                show.setCode(record.getCode());
                show.setShopId(record.getShopid());
                show.setShopName("第("+record.getShopqishu()+")期 "+record.getShopname());
                show.setGoNumber(record.getGonumber());
                String codes = record.getGoucode();
                show.setAllCodes(codes);
                String[] gouCodes = codes.split(",");
                show.setGouCodes(gouCodes);
                String huode = record.getHuode();
                if (huode != null) {
                    show.setHuode(huode);
                }
                if (huode == null) {
                    show.setHuode("");
                }

                String time = record.getTime().replace(".","");
                if (time != null) {
                    time = ElBase.fmtTime(Long.parseLong(time));
                    show.setTime(time);
                }
                showRecordVos.add(show);

            }
        }
        return showRecordVos;
    }

    /**
     * 查询 用户购买纪录 总数量
     * @param request
     * @param userId
     * @return
     */
    public Integer getShowTotalRecords (HttpServletRequest request,Integer userId){

        return goMemberGoRecordDAO.selectTotalRecordByUid(userId);

    }

    /**
     * 查询用户的 充值纪录
     * 分页
     *
     * @param request
     * @param userId
     * @return
     */
    public List<ShowAccountVo> getShowAccount(HttpServletRequest request,Integer userId,Integer pageNum) {

        int offSet = (pageNum-1)* ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet,ValueConsts.PAGE_SIZE);


        List<GoMemberAccount> accounts = goMemberAccountDAO.findAccountByUserId(userId,bounds);

        List<ShowAccountVo> showAccountVos = new ArrayList<>(accounts.size());

        if (accounts != null && accounts.size() != 0) {

            for (GoMemberAccount account : accounts) {

                Integer type = account.getType();

                if (type == 1) {

                    ShowAccountVo show = new ShowAccountVo();

                    show.setType("充值");
                    show.setMoney("充值"+account.getMoney());

                    show.setPay(account.getPay());
                    show.setContent(account.getContent());
                    String time = account.getTime();

                    time = ElBase.fmtTime(Long.parseLong(time)*1000);
                    show.setTime(time);

                    showAccountVos.add(show);
                }
            }
        }
        return showAccountVos;
    }

    /**
     * 获取此用户的 充值纪录的 总数
     * @param request
     * @param userId
     * @return
     */
    public int getShowTotalAccounts (HttpServletRequest request,Integer userId) {

        return goMemberAccountDAO.findTotalAccountByUserId(userId);
    }


    /**
     * 充值查询  分页
     * @param request
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    public List<ShowAccountVo> doGetRechargeData ( HttpServletRequest request,
                                                   String startTime, String endTime,
                                                   String paramKey, String paramValue, Integer pageNum) {


        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);

        List<GoMemberAccount> accounts = goMemberAccountDAO.findAccountByParamKeyParamValueTime (startTime,endTime,paramKey,paramValue,bounds);
        //把GoMemberAccount组装成一个ShowAccountVo
        List<ShowAccountVo> showAccountVos = new ArrayList<>(accounts.size());

        for (GoMemberAccount account : accounts) {

            ShowAccountVo show = new ShowAccountVo();

            Integer userId = account.getUid();
            show.setUserId(userId);
            GoMember member = goMemberDAO.selectByPrimaryKey(userId);
            if (member != null) {
                String userName = member.getUsername();
                if (userName == null) {
                    userName = "";
                }
                show.setUserName(userName);
                String phone = member.getMobile();
                if (phone == null){
                    phone = "";
                }
                show.setPhone(phone);
            }
            if (member == null) {
                show.setUserName("");
                show.setPhone("");
            }
            show.setType("充值");
            show.setMoney("充值"+account.getMoney());

            show.setPay(account.getPay());
            show.setContent(account.getContent());
            String time = account.getTime();

            String reg="^\\d+$";
            if (time.matches(reg)) {
                time = ElBase.fmtTime(Long.parseLong(time)*1000);
                show.setTime(time);
            } else {
                show.setTime("");
            }
            showAccountVos.add(show);
        }

        return showAccountVos;
    }

    /**
     * 充值纪录数量
     * @param request
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    public int doGetRechargeDataNum (HttpServletRequest request,
                                                  String startTime,
                                                  String endTime,
                                                  String paramKey,
                                                  String paramValue) {

        return goMemberAccountDAO.findAccountNumByParamKeyParamValueTime (startTime,endTime,paramKey,paramValue);

    }

    /**
     * 购买纪录查询  分页
     * @param request
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    public List<ShowRecordVo> doGetBuyData ( HttpServletRequest request,
                                                   String startTime, String endTime,
                                                   String paramKey, String paramValue, Integer pageNum) {


        int offSet = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offSet, ValueConsts.PAGE_SIZE);

        List<GoMemberGoRecord> records = goMemberGoRecordDAO.findRecordByParamKeyParamValueTime (startTime,endTime,paramKey,paramValue,bounds);
        //把GoMemberAccount组装成一个ShowAccountVo
        List<ShowRecordVo> showRecordVos = new ArrayList<>(records.size());

        for (GoMemberGoRecord record : records) {
            /**
             private Integer     userId;
             private String      userName;
             private String      phone;
             private String      code;
             private Integer     shopId;
             //商品名及期数:第 n 期 +title
             private String      shopName;
             private Integer     goNumber;
             private String      allCodes;
             private String[]    gouCodes;
             private Integer      lotteryOrNot;
             private String      huode;
             private String      time;
             */
            ShowRecordVo show = new ShowRecordVo();
            Integer userId = record.getUid();
            show.setUserId(userId);
            show.setUserName(record.getUsername());
            GoMember member = goMemberDAO.selectByPrimaryKey(userId);
            if (member != null) {
                String phone = member.getMobile();
                if (phone != null) {
                    show.setPhone(phone);
                }else {
                    show.setPhone("");
                }
            }else {
                show.setPhone("");
            }
            show.setCode(record.getCode());
            show.setShopId(record.getShopid());

            show.setShopName("第("+record.getShopqishu()+")期 "+record.getShopname());
            show.setGoNumber(record.getGonumber());

            String allCodes = record.getGoucode();
            show.setAllCodes(allCodes);
            String[] gouCodes = allCodes.split(",");
            show.setGouCodes(gouCodes);

            String huode = record.getHuode();
            if (huode != null) {
                show.setHuode(huode);
            }
            if (huode == null) {
                show.setHuode("");
            }

            String time = record.getTime().replace(".","");
            if (time != null) {
                time = ElBase.fmtTime(Long.parseLong(time));
                show.setTime(time);
            }
            showRecordVos.add(show);
        }

        return showRecordVos;
    }

    /**
     * 充值纪录数量
     * @param request
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param paramKey      查询元素    : uid,name等
     * @param paramValue    查询值     : 用户id,用户名
     * @return
     */
    public int doGetBuyDataNum (HttpServletRequest request,
                                     String startTime,
                                     String endTime,
                                     String paramKey,
                                     String paramValue) {

        return goMemberGoRecordDAO.findRecordNumByParamKeyParamValueTime (startTime,endTime,paramKey,paramValue);

    }
}
