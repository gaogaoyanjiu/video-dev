package com.tdtk.service.impl;

import com.tdtk.mapper.FilmMapper;
import com.tdtk.pojo.Film;
import com.tdtk.pojo.vo.FilmVO;
import com.tdtk.service.FilmService;
import com.tdtk.utils.Contents;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.n3r.idworker.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FilmServiceImpl  implements FilmService {

    @Autowired
    private FilmMapper filmMapper;


    @Override
    public List<Film> queryAll() {
        return filmMapper.queryAll();
    }

    public List<FilmVO> queryFaceImageAndLink() {

        List<FilmVO> filmVOList = filmMapper.queryFaceImageAndLink();

        return filmVOList;
    }


    //入口函数
    //http://www.tvvip.cn/film.html?cat=all&pageno=2
    public String startCrawler(String rootUrl, String subCatalogPage,String typeId, String startPage) throws Exception {
        String src = "";
        String baseUrl = "http://www.tvvip.cn";
        String catalogPage = "film";
        String cat = "all";

        String pageno = "&pageno={page}";//vip電影入口:http://www.tvvip.cn
        if (StringUtils.isNotEmpty(rootUrl)) {
            baseUrl = rootUrl;
        }
        if (StringUtils.isNotEmpty(subCatalogPage)) {
            catalogPage = "/" + subCatalogPage + ".html";
        }

        if (StringUtils.isNotBlank(typeId)) {
            cat = "?cat=" + typeId;
        }

        //拼接
        src = baseUrl + catalogPage + cat + pageno;


        //进入电影网站的list列表入口页面
        String startUrl = "";
        if (StringUtils.isNotEmpty(startPage)) {
            startUrl = src.replace("{page}", startPage);
        } else {
            startUrl = src.replace("{page}", "1");
        }

        System.out.println(startUrl);
        //获取页面
        String html = this.doGet(startUrl);

        //解析html页面
        Document doc = Jsoup.parse(html);


        //String text = doc.select("#js-ew-page a").text();//$("#js-ew-page a").length
        String text = doc.select("#js-tongjic a").text();

        Elements elements=doc.select("#js-tongjic a");

        String[] split = text.split("\\D+");
//	 for (int i=0;i<split.length;i++) {
//		 if(i==split.length-1){
//			 System.out.println(split[i]);
//		 }
//	}

        if(split.length>0){


        Integer totalPage = Integer.parseInt(split[split.length - 1]);

        if (StringUtils.isNotEmpty(startPage) && Integer.valueOf(startPage) < totalPage) {

        for (int i = 1; i <= totalPage; i++) {
            String url = src.replace("{page}", String.valueOf(i));
            //System.out.println(url);

            //获取页面
            String subHtml = this.doGet(url);

            //解析html页面
            Document subDoc = Jsoup.parse(subHtml);

            Elements lis = subDoc.select(".list li");//$(".list li")
//		<div class="cover g-playicon">
//		 <img src="http://p2.qhimg.com/d/dy_2fc3f1505615d3f9eb713a134a50f134.jpg">
//		 <span class="pay">推荐</span>
//		 <span class="hint">2018</span>
//		</div>
            for (Element element : lis) {

                Element child = element.child(0);
                String a_link = child.attr("href");//获取a标签

                String html2 = element.html();
                System.out.println(html2);

                Elements elementsByTag = element.getElementsByTag("div");
                int size = elementsByTag.size();

                System.out.println("===============================================================>第"+i+"页");
                //电影海报封面图
                String videoImage = "";
                //是否推荐
                String videoPay = "";
                //上映时间
                String videoPlay = "";
                //电影名称
                String videoName = "";
                //主演
                String videoStar = "";
                //电影描述
                String videoDesc = "";

                //电影评分
                String evaluation = "";

                for (int k = 0; k < size; k++) {


                    if (k == 0) {
//                         <img src="http://p2.qhimg.com/d/dy_2fc3f1505615d3f9eb713a134a50f134.jpg">
//                         <span class="pay">推荐</span>
//                         <span class="hint">2018</span>

                        Element element2 = elementsByTag.get(k);

                        System.out.println("====================================================================>a标签里边的内容");
                        System.out.println(element2.html());
                        videoImage = element2.child(0).attr("src");//电影海报封面图
                        int childNodeSize = element2.childNodeSize();
                        if(childNodeSize>2){
                            //索引0是图片
                            videoPay = element2.child(1).text();//是否推荐
                            //如果等于推荐，索引总长度为3，否则为2
                            if ("推荐".equals(element2.child(1).text())) {
                                videoPlay = element2.child(2).text();//上映时间
                            }

                        }else {
                                videoPay = "否";//是否推荐
                                videoPlay = element2.child(1).text();//上映时间
                        }



                    } else if (k == 1) {
//                          <p class="title g-clear"> <span class="s1">厨霸江湖</span> <span class="s2"></span> </p>
//                          <p class="star">主演：王宥钧 刘名凯 靳锦 黄子星</p>
                        Element element2 = elementsByTag.get(k);
                        //System.out.println(element2.html());
                        videoName = element2.child(0).text();//电影名称
                        videoStar = element2.child(1).text();//主演
                    }
                }
                //获取a标签
//                String[] sp= startUrl.split("\\?");//
                System.out.println("a标签：" + baseUrl + a_link);
                //获取页面
                String subSrc = doGet(baseUrl + a_link);
                //解析html页面
                Document subSrcDoc = Jsoup.parse(subSrc);
                Elements selects = subSrcDoc.select("p.item-desc");
                videoDesc = selects.get(0).toString();//$("p.item-desc")[0].innerText
//              "传说在古代的中华料理之中，有一派厨师因将武功融入厨艺当中做出绝味美食在厨坛名声大噪，世人称之为—武厨。而后百年间，各武厨门派为争夺“天下第一厨”之称而斗争不断。为了保卫家园，功夫少年小吉利与“天下第一厨”六扇肉的大弟子味极立下赌约，在京城召开的天下料理大赛上一决高下。一场关于代表草根美食的黑暗料理和山珍海味的高级料理之战也随之拉开序幕。"


                //电影海报封面图
                System.out.println("电影海报封面图：" + videoImage);
                //是否推荐
                System.out.println("是否推荐：" + videoPay);
                //上映时间
                System.out.println("上映时间：" + videoPlay);
                //电影名称
                System.out.println("电影名称：" + videoName);

                String[] splitA = videoName.split(" ");
                Double aDouble =null;
                if (splitA.length>1){
                    evaluation= splitA[splitA.length-1];
                    aDouble = Double.valueOf(evaluation);
                }else {
                    aDouble = 0d;
                }
                //电影评分
                System.out.println("电影评分：" + aDouble);


                //主演
                System.out.println("主演：" + videoStar);

                //电影描述
                System.out.println("电影描述：" + videoDesc);

                Film film = new Film();
                IdWorker idWorker = new IdWorker(0);
                String id = idWorker.nextId() + "";

                film.setId(id);
                film.setActor("未知");//作者
                film.setCatalogName(Contents.getCatalogVideoMap().get(subCatalogPage));//视频分类
                film.setCatalogId(subCatalogPage);//视频分类id
                film.setEvaluation(aDouble);//影评分数                  //=====》此字段不能是null
                film.setImage(videoImage);//封面图片路径
                film.setIsUse(1);//是否能用                        //=====》此字段不能是null
                film.setLocName("大陆");//影片产地
//               // film.setLocId("f39c979857a4c8c50157a9002ff9001c");//影片产地ID
                film.setName(videoName);//影片名称
                film.setOnDecade(videoPlay);//上映时间
                film.setResolution("1008");//清晰度
                film.setStatus("全集");//影片状态
//                film.setSubclassname("动作片");//影片2级分类
//                film.setSubclassId("f3988888");//影片2级分类id
                film.setTypeId(typeId);//影片分类id
                film.setTypeName(Contents.getFilmMap().get(typeId));//影片分类


                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
                film.setUpdateTime(format.format(new Date()));//更新时间
                film.setIsVip(1);//是否是vip
                film.setPlot(videoDesc);//剧情描述
                film.setPageLink(baseUrl + a_link);//a标签页面跳转链接


                // System.out.println("查询："+filmMapper.queryAll());

                int flag = filmMapper.insert(film);//保存电影基础信息
                if (flag == 1) {
                    System.out.println("保存成功：" + flag);
                }
            }
        }
        }
    }
        //System.out.println(text);
        return "爬取完成...";
    }


    //获取html页面
    private String doGet(String html) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();


        //将字符串转换为url
        URL url1 = new URL(html);
        URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);


        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
//		HttpGet httpGet = new HttpGet("http://www.baidu.com/");

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
//				FileUtils.writeStringToFile(new File("E:\\dataVideo\\film.html"), content, "UTF-8");
//				System.out.println("内容长度：" + content.length());


                System.out.println("获取页面>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
            httpClient.close();
        }
        return "";
    }
}
