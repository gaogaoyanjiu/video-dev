package com.tdtk.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

//电视信息-子表
@Table(name = "tv_play")
public class TvPlay {
    @Id
    private String id; //主键
    //新增字段
    @Column(name = "tv_name")
    private String tvName;//电影名称;

    @Column(name = "tv_publish_time")
    private String tvPublishTime;//发布时间

    @Column(name = "tv_is_recommend")
    private String tvIsRecommend;//是否推荐

    @Column(name = "tv_type")
    private String tvType;//电影类型

    @Column(name = "tv_episodes")
    private String tvEpisodes; //集数


    @Column(name = "tv_isdel")
    private String tvIsdel;//是否删除

    @Column(name = "tv_createtime")
    private String tvCreateTime;//创建时间

    @Column(name = "tv_updatetime")
    private String tvUpdateTime;//更新时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvPublishTime() {
        return tvPublishTime;
    }

    public void setTvPublishTime(String tvPublishTime) {
        this.tvPublishTime = tvPublishTime;
    }

    public String getTvIsRecommend() {
        return tvIsRecommend;
    }

    public void setTvIsRecommend(String tvIsRecommend) {
        this.tvIsRecommend = tvIsRecommend;
    }

    public String getTvType() {
        return tvType;
    }

    public void setTvType(String tvType) {
        this.tvType = tvType;
    }

    public String getTvEpisodes() {
        return tvEpisodes;
    }

    public void setTvEpisodes(String tvEpisodes) {
        this.tvEpisodes = tvEpisodes;
    }

    public String getTvIsdel() {
        return tvIsdel;
    }

    public void setTvIsdel(String tvIsdel) {
        this.tvIsdel = tvIsdel;
    }

    public String getTvCreateTime() {
        return tvCreateTime;
    }

    public void setTvCreateTime(String tvCreateTime) {
        this.tvCreateTime = tvCreateTime;
    }

    public String getTvUpdateTime() {
        return tvUpdateTime;
    }

    public void setTvUpdateTime(String tvUpdateTime) {
        this.tvUpdateTime = tvUpdateTime;
    }
}