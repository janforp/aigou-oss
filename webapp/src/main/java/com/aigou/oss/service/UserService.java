package com.aigou.oss.service;

import com.aigou.oss.config.Config;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.dao.GoMemberDizhiDAO;
import com.aigou.oss.dao.GoShaidanDAO;
import com.aigou.oss.model.GoMember;
import com.aigou.oss.model.GoMemberDizhi;
import com.aigou.oss.model.GoShaidan;
import com.aigou.oss.model.vo.LotteryUserInfo;
import com.aigou.oss.model.vo.UpdataShaidanVo;
import com.aigou.oss.model.vo.UserInfo;
import com.aigou.oss.util.CommonMethod;
import com.aigou.oss.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Summer on 16/6/14.
 */
@Service
public class UserService {

    @Autowired
    private GoMemberDAO goMemberDAO;
    @Autowired
    private GoMemberDizhiDAO goMemberDizhiDAO;
    @Autowired
    private GoShaidanDAO goShaidanDAO;


    /**
     * 发货页面的 中奖用户信息
     * @param userId
     * @return
     */
    public LotteryUserInfo selectLotteryUserInfo (Integer userId) {

        GoMember member = goMemberDAO.selectByPrimaryKey(userId);
        LotteryUserInfo info = null;
        if (member != null) {

            info = new LotteryUserInfo();

            info.setUserId(member.getUid());
            String phone = member.getMobile();
            if (phone == null) {
                phone = "";
            }
            info.setPhone(phone);
            String userName = member.getUsername();
            if (userName == null) {
                userName = "";
            }
            //显示在收货地址栏上的手机,最好是地址表中的手机
            GoMemberDizhi dizhi = goMemberDizhiDAO.selectAddressByUidAndDefault(userId);

            if (dizhi != null) {

                String phoneInDizhi = dizhi.getMobile();
                if (phoneInDizhi == null) {
                    phoneInDizhi = phone;
                }

                String shouhuoren = dizhi.getShouhuoren();
                if (shouhuoren == null) {
                    shouhuoren = "";
                }

                String sheng = dizhi.getSheng();
                if (sheng == null) {
                    sheng = "";
                }
                String shi = dizhi.getShi();
                if (shi == null) {
                    shi = "";
                }
                String xian = dizhi.getXian();
                if (xian == null) {
                    xian = "";
                }
                String jiedao = dizhi.getJiedao();
                if (jiedao == null) {
                    jiedao = "";
                }

                String realName = dizhi.getShouhuoren();
                if (realName == null) {
                    realName = userName;
                }

                String otherPhone = dizhi.getMobile();
                if (otherPhone == null) {
                    otherPhone = phone;
                }

                info.setOtherPhone(otherPhone);
                info.setUserName(realName);
                info.setProvinceCity(sheng+shi+xian);
                info.setDetailAddress("收货人姓名: " + shouhuoren + ",详细地址: " + sheng + shi + xian + jiedao + ",手机号: " + phoneInDizhi);
            }
            if (dizhi == null) {

                List<GoMemberDizhi> dizhis = goMemberDizhiDAO.selectAddressesByUid(userId);
                if (dizhis == null || dizhis.size() == 0) {
                    info.setDetailAddress("该用户没有默认地址,也没有填写非默认地址");
                } else {
                    //取出第一条地址作为发货地址
                    GoMemberDizhi firstDizhi = dizhis.get(0);

                    String phoneInDizhi = firstDizhi.getMobile();
                    if (phoneInDizhi == null) {
                        phoneInDizhi = phone;
                    }

                    String shouhuoren = firstDizhi.getShouhuoren();
                    if (shouhuoren == null) {
                        shouhuoren = "";
                    }

                    String sheng = firstDizhi.getSheng();
                    if (sheng == null) {
                        sheng = "";
                    }
                    String shi = firstDizhi.getShi();
                    if (shi == null) {
                        shi = "";
                    }
                    String xian = firstDizhi.getXian();
                    if (xian == null) {
                        xian = "";
                    }
                    String jiedao = firstDizhi.getJiedao();
                    if (jiedao == null) {
                        jiedao = "";
                    }
                    String realName = dizhi.getShouhuoren();
                    if (realName == null) {
                        realName = userName;
                    }

                    String otherPhone = dizhi.getMobile();
                    if (otherPhone == null) {
                        otherPhone = phone;
                    }

                    info.setOtherPhone(otherPhone);
                    info.setUserName(realName);
                    info.setProvinceCity(sheng+shi+xian);
                    info.setDetailAddress("收货人姓名: " + shouhuoren + ",详细地址: " + sheng + shi + xian + jiedao + ",手机号: " + phoneInDizhi);
                }
            }
        }
        return info;
    }


    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public UserInfo selectUser(Integer userId) {

        GoMember member = goMemberDAO.selectByPrimaryKey(userId);

        if(member != null) {

            UserInfo info = new UserInfo();

            String photo = member.getImg();
            if(!photo.startsWith("http://")) {
                photo = Config.getImageBaseUrl() + photo;
            }
            info.setImg(photo);
            info.setUserId(member.getUid());
            info.setUserName(member.getUsername());
            String phone = member.getMobile();
            if (phone != null && phone.trim().length() != 0) {
                info.setPhone(phone);
                info.setOtherPhone(phone);
            }

            GoMemberDizhi dizhi = goMemberDizhiDAO.selectAddressByUidAndDefault(userId);

            if (phone == null){
                phone="";
                //注册手机
                info.setPhone(phone);
                //但是电话卡充值的电话号码可以用其它的电话,需要传到页面
                //先找绑定手机号(绑定手机号也会存在go_member表)
                //再去地址表中找
                if (dizhi != null) {
                    info.setOtherPhone(dizhi.getMobile());
                }
            }
            if(dizhi != null) {
                info.setProvinceCity(dizhi.getSheng()+dizhi.getShi()+dizhi.getXian());
                info.setDetailAddress(dizhi.getJiedao());
            }
            info.setEmail(member.getEmail());


            return info;

        }

        return null;

    }

    /**
     * 好亲切晒单对象
     * @param request
     * @param sdId
     * @return
     */
    public UpdataShaidanVo getSUpdataShaidanVoById(HttpServletRequest request, Integer sdId) {

        GoShaidan shaidan =  goShaidanDAO.selectByPrimaryKey(sdId);

        return turnGoShaidanToUpdataShaidanVo(shaidan);

    }

    /**
     * 转换
     * @param shaidan
     * @return
     */
    public UpdataShaidanVo turnGoShaidanToUpdataShaidanVo(GoShaidan shaidan) {

        UpdataShaidanVo vo = new UpdataShaidanVo();

        vo.setSdId(shaidan.getSdId());

        vo.setSdUserid(shaidan.getSdUserid());
        vo.setSdShopid(shaidan.getSdShopid());
        vo.setSdQishu(shaidan.getSdQishu());
        vo.setSdTitle(shaidan.getSdTitle());
        vo.setSdThumbs(shaidan.getSdThumbs());
        String content = shaidan.getSdContent();
        if (StringUtil.isEmpty(content)) {
            content = "";
        }
        vo.setSdContent(content);
        String sdPhotolist = shaidan.getSdPhotolist();
        vo.setSdPhotolist(sdPhotolist);
        List<String > photos = null;
        if (!StringUtil.isEmpty(sdPhotolist)) {

            String[] pics = sdPhotolist.split(";");
            photos = new ArrayList<>(pics.length);
            photos = Arrays.asList(pics);

            vo.setPhotos(photos);
        }
        return vo;
    }

    /**
     * 修改用户信息
     * @param userId
     * @param userName
     * @param userPhoto
     * @return
     */
    public int updateUser(Integer userId, String userName, String userPhoto) {

        return goMemberDAO.updateUser(userId, userName, userPhoto);

    }

    /**
     * 过滤昵称中的特殊字符
     * @param name
     * @return
     */
    public String filterName(String name) {

        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");

        StringBuffer newName = new StringBuffer();

        int maxlength = CommonMethod.getMinValue(name.length(), 50);

        for (int i = 0; i < maxlength; i++) {

            String ch = String.valueOf(name.charAt(i));

            Matcher m = p.matcher(ch);

            if(m.matches()) {
                newName.append(ch);
            }

        }

        return newName.toString();

    }

}
