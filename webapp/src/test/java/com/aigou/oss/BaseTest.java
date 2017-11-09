package com.aigou.oss;

import com.aigou.oss.dao.GoShoplistDAO;
import com.aigou.oss.model.GoShoplist;
import org.apache.ibatis.session.RowBounds;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author wwg
 *         测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/spring/spring-profile.xml"})
@ActiveProfiles(profiles = "development")
public class BaseTest {

    @Autowired
    private GoShoplistDAO goShoplistDAO;

    @Test
    public void testLoad() {
        System.out.println("test");
    }

    @Test
    public void testSelectUnreleaseCount() {
        Integer a = goShoplistDAO.selectUnreleaseCount(null,null,"userId","4",null);
        System.out.println(">>>"+a);
    }

    @Test
    public void testSelectUnreleaseOrder() {

        RowBounds bounds = new RowBounds(0, 10);

        List<GoShoplist> a = goShoplistDAO.selectUnreleaseOrder(null,null,"userId","2819","1","0",bounds);
        System.out.println(">>>"+a);
    }
    @Test
    public void testJsoup () throws IOException {

        Document document = Jsoup.parse(new URL("http://item.jd.com/1757283820.html"),5000);
        Element bod = document.getElementById("name");
        String name = bod.text();
        System.out.println("<<<<<<"+name);
    }

}
