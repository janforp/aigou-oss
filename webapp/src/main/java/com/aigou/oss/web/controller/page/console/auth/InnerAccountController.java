package com.aigou.oss.web.controller.page.console.auth;

import com.aigou.oss.consts.RequestConsts;
import com.aigou.oss.model.form.CreateAccountForm;
import com.aigou.oss.service.InnerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.*;

/**
 * Created by Jan on 16/6/22.
 *
 * 机器人
 */

@Controller
@RequestMapping(value = "/page/console/auth/innerUsers",produces = RequestConsts.CONTENT_TYPE_HTML,method = {RequestMethod.GET,RequestMethod.POST})
public class InnerAccountController {

    @Autowired
    private InnerAccountService innerAccountService;

    /**
     * 点击显示 上传文件 页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/createInnerUsers")
    public String createInnerAccount (HttpServletRequest request) {

        return "create_account";
    }


    /**
     * 读取上传的文件中的内容,去除空格及空行
     * @param request
     * @param fileup      文件上传框中的name属性
     * @return
     * @throws IOException
     * @throws ServletException
     */
//    @RequestMapping(value = "/readFile")
//    @ResponseBody
//    public String readFileToList(HttpServletRequest request , MultipartFile fileup) throws IOException, ServletException {
//
//        //得到 总的 名字
//        List<String> names =  innerAccountService.doReadFileToList(request,fileup);
//        //得到 总的 IP
//        List<String> IPs =   innerAccountService.getRandomIPList(names);
//        //得到 总的 地址信息+IP
//        //List<String > regionIPs = innerAccountService.getRegionIPs(IPs);
//        //再存入数据库中,先不加地址信息
//        return innerAccountService.createAccounts(names,IPs);
//
//    }

    /**
     * 试图用model封装form表单提交的参数,并能够
     * 在form请求中指向ajax回调的方法
     *
     * @param request
     * @param createAccountForm
     * @return
     * @throws IOException
     * @throws ServletException
     */

    @RequestMapping(value = "/readFile")
    @ResponseBody
    public String readFile(HttpServletRequest request,
                           @Valid CreateAccountForm createAccountForm) throws IOException, ServletException {

        MultipartFile file = createAccountForm.getTxtFile();
        // 得到 总的 名字
        List<String > names = innerAccountService.doReadFileToList(request,file);
        // 得到 总的 IP
        List<String> IPs = innerAccountService.getRandomIPList(names);
        return innerAccountService.createAccounts(names, IPs);
    }



    /**
     * 把GoMember表中没有插入ag_inner_account表的机器人插入内部表
     * @param request
     * @return
     */
    @RequestMapping(value = "/putRobotToInner")
    @ResponseBody
    public String putRobotToInner(HttpServletRequest request) {

       return innerAccountService.doPutRobotToInner(request);
    }

}
