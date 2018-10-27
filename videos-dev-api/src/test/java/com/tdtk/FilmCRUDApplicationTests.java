package com.tdtk;

import com.tdtk.mapper.FilmMapper;
import com.tdtk.pojo.Film;
import com.tdtk.pojo.vo.FilmVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.n3r.idworker.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * FilmCRUD测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FilmCRUDApplicationTests {

    @Autowired
    private FilmMapper filmMapper;

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @Test
    public void test(){
        System.out.println("test");
    }





    //保存电影信息到数据库
    @Test
    public void saveFilm() throws Exception {

        Film film = new Film();
        IdWorker idWorker = new IdWorker(0);
        //String id = sid.nextShort();
        String recordsId = idWorker.nextId()+"";
        film.setId(recordsId);
        film.setActor("张三");//作者
        film.setCatalogName("电影");//视频分类
        film.setCatalogId("f0000000");//视频分类id
        film.setEvaluation(8d);//影评分数
        film.setImage("public/static/upload/filmPic/333333.jpg");//封面图片路径
        film.setIsUse(1);//是否能用
        film.setLocName("美国");//影片产地
        film.setLocId("f39c979857a4c8c50157a9002ff9001c");//影片产地ID
        film.setName("肖申克的救赎5");//影片名称
        film.setOnDecade("2008");//上映时间
        film.setResolution("1008");//清晰度
        film.setStatus("全集");//影片状态
        film.setSubClassName("动作片");//影片2级分类
        film.setSubClassId("f3988888");//影片2级分类id
        film.setTypeId("f3955555555");//影片分类id
        film.setTypeName("爱情片");//影片分类
        film.setUpdateTime("2018-09-30 08:00:00");//更新时间
        film.setIsVip(0);//是否是vip
        film.setPlot("<p>终身监禁的惩罚无疑注定了安迪接下来灰暗绝望的人生。</p>");//剧情描述

        int flag = filmMapper.insert(film);
            if(flag==1){
                System.out.println("保存成功："+flag);
            }
    }

    //查询电影信息
    @Test
    public void qyeryFilm() throws Exception {

//        select * from t_film f LEFT JOIN t_res r ON f.id=r.film_id
       // List<Film> films = filmMapper.queryAll();
        List<FilmVO> films = filmMapper.queryFaceImageAndLink();
        if (films.size()>0){
            FilmVO filmVO = films.get(0);
//        System.out.println("视频连接地址："+filmVO.getLink());
//        System.out.println("视频分类级别："+filmVO.getEpisodes());
//        System.out.println("视频连接类型："+filmVO.getLinkType());
//        System.out.println("影片主键id："+filmVO.getFilm_id());
        }

    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
