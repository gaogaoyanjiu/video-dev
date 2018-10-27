package com.tdtk;

import com.tdtk.service.FilmService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试爬虫的demo示例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class PageParseApplicationTests {

//    主 页  http://www.tvvip.cn/
//    看电影 http://www.tvvip.cn/film.html
//    追热剧 http://www.tvvip.cn/tvplay.html
//    综艺   http://www.tvvip.cn/va.html
//    动漫   http://www.tvvip.cn/cartoon.html

//    document.getElementById("clickMe").click();


    @Autowired
    private FilmService filmService;

    private String START_URL="";
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
        //进入电影网站的list列表入口页面
        START_URL="http://www.tvvip.cn";//vip電影入口:http://www.tvvip.cn
//      START_URL="http://www.tvvip.cn/film.html?cat=all&pageno={page}";//vip電影入口:http://www.tvvip.cn
    }


    //解析网页
    @Test
    public void test() throws Exception {
        System.out.println("解析网页");
       // filmService.startCrawler(START_URL,"/film.html","1");//入口url
    }




    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
