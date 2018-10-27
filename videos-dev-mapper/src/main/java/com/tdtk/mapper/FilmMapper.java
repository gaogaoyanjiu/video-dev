package com.tdtk.mapper;

import com.tdtk.pojo.Film;
import com.tdtk.pojo.vo.FilmVO;
import com.tdtk.utils.MyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FilmMapper extends MyMapper<Film> {

    @Select("select * from t_film")
     List<Film> queryAll();//查询所有

//    @Select(" select " +
//            " f.id,f.actor,f.catalog_name,f.evaluation,f.image,f.is_use,f.loc_name,f.loc_id, " +
//            " f.name,f.on_decade,f.plot,f.resolution,f.status,f.sub_class_name,f.sub_class_id, " +
//            " f.type_name,f.type_id,f.update_time,f.is_vip,r.episodes,r.link,r.link_type,r.film_id " +
//            " from " +
//            " t_film f,t_film_res r " +
//            " where " +
//            " 1 = 1 and f.id = r.film_id ")

    @Select(" select * from " +
            " t_film f,t_film_res r " +
            " where 1 = 1 and f.id = r.film_id ")
    List<FilmVO> queryFaceImageAndLink();//查询影片的封面和link地址

}