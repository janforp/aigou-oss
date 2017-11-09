package com.aigou.oss.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Copy by Jan on 16/6/22 from taobao .
 * HTTP工具箱
 */
@Service
public  class ImitateSendRequestService {

    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     *
     * @param url              请求的URL地址
     * @param queryString      请求的查询参数,可以为null
     * @param charset          字符集
     * @return 返回请求响应的HTML
     */
    public  String doGet(String url, String queryString, String charset) throws IOException {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
            if (StringUtils.isNotBlank(queryString))
                //对get请求参数做了http请求默认编码，没有任何问题，汉字编码后，就成为%式样的字符串
                method.setQueryString(URIUtil.encodeQuery(queryString));
            client.executeMethod(method);
            client.setTimeout(0);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                        response.append(line);
                }
                reader.close();

            }
        return response.toString();
    }

//    public static void main(String[] args) throws IOException {
//
//        RandomIpService randomIpService = new RandomIpService();
//
//        String randomIp = randomIpService.getRandomIp();
//
//        ImitateSendRequestService imit = new ImitateSendRequestService();
//        String y = imit.doGet("http://ip.taobao.com/service/getIpInfo.php?ip="+randomIp, null, "GBK");
//        TaobaoIP taobaoIP = JSON.parseObject(y, TaobaoIP.class);
//        IPdata ip = taobaoIP.getData();
//
//        System.out.println(ip.getCountry()+":"+ip.getArea()+":"+ip.getRegion()+":"+ip.getCity()+":"+ip.getCounty()+":"+ip.getIsp());
//    }
}