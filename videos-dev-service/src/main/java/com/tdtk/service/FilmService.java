package com.tdtk.service;

import com.tdtk.pojo.Film;
import com.tdtk.pojo.vo.FilmVO;

import java.util.List;

public interface FilmService {

    /**
     *查询视频所有列表
     * @return
     */
    public List<Film> queryAll();


    /**
     *查询视频列表
     * @return
     */
    public List<FilmVO> queryFaceImageAndLink();


    /**
     *执行爬虫程序
     * @return
     */
    String startCrawler(String rootUrl,String subCatalogPage,String typeId, String startPage) throws Exception;

}
