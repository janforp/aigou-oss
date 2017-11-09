package com.aigou.oss.service;

import com.aigou.oss.dao.AgInnerAccountDAO;
import com.aigou.oss.dao.GoMemberDAO;
import com.aigou.oss.model.AgInnerAccount;
import com.aigou.oss.model.GoMember;
import com.aigou.oss.model.vo.IPdata;
import com.aigou.oss.model.vo.TaobaoIP;
import com.aigou.oss.util.JsonUtil;
import com.aigou.oss.util.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/6/22.
 * 机器人
 */
@Service
public class InnerAccountService {

    @Autowired
    private RandomIpService randomIpService;
    @Autowired
    private ImitateSendRequestService imitateSendRequestService;
    @Autowired
    private GoMemberDAO goMemberDAO;
    @Autowired
    private AgInnerAccountDAO agInnerAccountDAO;
    @Autowired
    private UserService userService;


    /**
     * 把页面上传的文件中的每行字符串存入集合中
     * 用作userName
     *
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public List<String> doReadFileToList(HttpServletRequest request , MultipartFile file) throws IOException, ServletException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(),"utf-8"));
        String name = null;
        List<String> list = new ArrayList<>();
        while ((name = reader.readLine()) != null) {

            if (name.trim()==""||name.trim().length()==0) {
                continue;
            }
            //把机器人的名字存入list集合
            list.add(name.replaceAll(" ","").trim());
        }
        return list;

    }


    /**
     * 把文件中得到的 机器人的names 分配随机的ip
     * 并且 生成一个GoMemeber对象存入表,把userId存入
     * ag_inner_account表
     *
     * @param names         名字
     * @param regionIPs     ip
     * @return
     * @throws IOException
     */
    public String createAccounts (List<String> names,List<String> regionIPs) throws IOException {

        for (int i = 0; i< names.size() ; i++) {

            String userName = names.get(i);

            userName = userService.filterName(userName);

            String user_ip = regionIPs.get(i);

            BigDecimal def = new BigDecimal(0);
            GoMember goMember = new GoMember();

            goMember.setUsername(userName);
            goMember.setPassword("123456");

            goMember.setUserIp(user_ip);

            goMember.setImg("http://image.lswuyou.cn/portraits/user.jpg");

            goMember.setEmailcode("-1");
            goMember.setMobilecode("-1");
            Integer time = (int) (System.currentTimeMillis() / 1000);
            goMember.setTime(time);
            goMember.setLoginTime(time);
            goMember.setSignInTime(0);
            goMember.setSignInDate("");
            goMember.setSignInTimeAll(0);
            goMember.setYongjin(def);
            goMember.setY1(def);
            goMember.setY2(def);
            goMember.setY3(def);
            goMember.setY4(def);
            goMember.setY5(def);
            goMember.setScore(0);
            goMember.setMoney(def);
            goMemberDAO.insert(goMember);

            Integer userId = goMember.getUid();

            //把刚生成的useId集合存入ag_inner_account表
            agInnerAccountDAO.insert(new AgInnerAccount(userId));

            System.out.println("插入 : "+i);
            }

        return JsonUtil.buildSuccess("成功插入"+names.size()+"条数据");
    }

    /**
     * 生成与文件中名字数量相同的 ip
     *
     * @param list  上传的文件中 读取到的总条数
     * @return
     */
    public List<String>  getRandomIPList (List<String> list) {

        List<String> IPs = new ArrayList<>(list.size());

        for (int i =1 ; i<=list.size() ; i++ ) {

            System.out.println("IP : "+i);

            String randomIP = randomIpService.getRandomIp();
            IPs.add(randomIP);
        }

        return IPs;
    }


    /**
     * 根据上面生成的IP,模拟请求获得这些IP的具体地址信息,也存入集合中
     * @param IPs
     * @return
     * @throws IOException
     */
    public List<String > getRegionIPs (List<String > IPs) throws IOException {


        String  url = "http://ip.taobao.com/service/getIpInfo.php?ip=";

        List<String> user_ips = new ArrayList<>(IPs.size());
        int a = 1;
        for (String IP : IPs ) {

            System.out.println("IP地址 : "+a);
            a++;
            String json = imitateSendRequestService.doGet(url+IP, null, "GBK");

            //在此最好能判断下,返回的字符串是否是标准的json格式的字符串,若不是,则直接使用IP,不要地址信息

            if (!json.startsWith("{")) {
                json = "{"+json;
            }

            TaobaoIP taobaoIP = JSON.parseObject(json,TaobaoIP.class);

            IPdata iPdata = taobaoIP.getData();

            String region =iPdata.getRegion();

            String city = iPdata.getCity();

            //则,生成数据需要的 IP 信息: IP,+region+city

            String user_ip = region+city+","+IP;

            user_ips.add(user_ip);

        }

        return user_ips;
    }











    /**
     * 把GoMember表中没有插入ag_inner_account表的机器人插入内部表
     * @param request
     * @return
     */
    public String doPutRobotToInner(HttpServletRequest request) {

        /**
         * 机器人id[0,6732],[6833,11837],
         * ('11847', '23078', '23495', '29236', '6756', '32918',
         * '24477', '6733', '6735', '12465', '12024', '11849', '6823')
         */

        List<Integer> uids = new ArrayList<>();
        for(int i=1 ;i<=6732;i++) {
            uids.add(i);
        }
        for(int i=6833;i<=11837;i++) {
            uids.add(i);
        }
        uids.add(11847);
        uids.add(23078);
        uids.add(23495);
        uids.add(29236);
        uids.add(6756);
        uids.add(32918);
        uids.add(24477);
        uids.add(6733);
        uids.add(6735);
        uids.add(12465);

        uids.add(12024);
        uids.add(11849);
        uids.add(6823);

        int successNums = 0;
        int failNums = 0;

        for (Integer userId : uids ) {

            GoMember member = goMemberDAO.selectByPrimaryKey(userId);

            if(member != null) {

                AgInnerAccount agInnerAccount = agInnerAccountDAO.selectByPrimaryKey(userId);

                if(agInnerAccount != null) {
                    failNums++;
                }
                if(agInnerAccount == null) {
                    agInnerAccountDAO.insert(new AgInnerAccount(userId));
                    successNums++;
                }
            }
        }
        return JsonUtil.buildSuccess("成功插入:"+successNums+"条,其余"+failNums+"条已经存在!");
    }
}
