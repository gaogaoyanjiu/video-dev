package com.tdtk.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

//电影信息-子表
@Table(name = "t_film_res")
public class FilmRes {
    @Id
    private String id; //主键
    //新增字段
    @Column(name = "episodes")
    private String episodes;//集数;

    @Column(name = "link")
    private String link; //视频链接地址

    @Column(name = "linkType")
    private String linkType;//视频类型


    @Column(name = "film_id")
    private String filmId;//主表id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
}