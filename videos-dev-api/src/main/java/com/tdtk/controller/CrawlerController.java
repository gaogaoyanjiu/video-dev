package com.tdtk.controller;

import com.tdtk.pojo.Film;
import com.tdtk.pojo.vo.FilmVO;
import com.tdtk.service.FilmService;
import com.tdtk.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "测试网络爬虫",tags = "测试网络爬虫的接口")
@RestController
public class CrawlerController {

	@Autowired
	private FilmService filmService;


    @ApiOperation(value="查询全部视频封面列表", notes="查询全部视频封面列表的接口")
    @PostMapping("/queryFilmAll")
    public JSONResult queryFilmAll() {

        List<Film> filmVOList = filmService.queryAll();

        return JSONResult.ok(filmVOList);
    }


	@ApiOperation(value="关联查询电影视频链接列表信息", notes="关联查询电影视频链接列表信息的接口")
	@PostMapping("/queryFilmList")
	public JSONResult queryFilmList() {

        List<FilmVO> filmVOList = filmService.queryFaceImageAndLink();

        return JSONResult.ok(filmVOList);
	}


//    主 页  http://www.tvvip.cn/
//    看电影 http://www.tvvip.cn/film.html
//    追热剧 http://www.tvvip.cn/tvplay.html
//    综艺   http://www.tvvip.cn/va.html
//    动漫   http://www.tvvip.cn/cartoon.html



    @ApiOperation(value="开始抓取网络数据到数据库", notes="开始抓取网络数据到数据库的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rootUrl",value = "爬取的根URL",required = false,dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "subCatalogPage",value = "子分类页面",required = false,dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "typeId",value = "电影类型",required = false,dataType = "String",paramType = "form"),
            @ApiImplicitParam(name = "startPage",value = "起始页码",required = false,dataType = "String",paramType = "form")
    })
    @PostMapping("/startCrawler")
    public JSONResult startCrawler(String rootUrl,String subCatalogPage,String typeId,String startPage) throws Exception {

        String result=filmService.startCrawler(rootUrl,subCatalogPage,typeId,startPage);

        return JSONResult.ok(result);
    }
	
}
