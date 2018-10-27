package com.tdtk.pojo;

import javax.persistence.*;

//爬取电影信息-主表
@Table(name = "t_film")
public class Film {
    @Id
    private String id; //主键

    @Column(name = "actor")
    private String actor; //导演

    @Column(name = "catalog_name")
    private String catalogName;  //电影大类目

    @Column(name = "catalog_id")
     private String catalogId; //电影大类目id

    @Column(name = "evaluation")
    private Double evaluation;  //集数

    @Column(name = "image")
    private String image;     //封面地址

    @Column(name = "is_use")
    private Integer isUse;    //是否可用

    @Column(name = "loc_name")
    private String locName;   //地域名称

    @Column(name = "loc_id")
    private String locId;    //地域名称id

    @Column(name = "name")
    private String name;    //电影名称

    @Column(name = "on_decade")
    private String onDecade;   //发布日期

    @Column(name = "resolution")
    private String resolution;  //清晰度

    @Column(name = "status")
    private String status;    //状态：全集

    @Column(name = "sub_class_name")
    private String subClassName; //二级分类名称

    @Column(name = "sub_class_id")
    private String subClassId;    //二级分类id

    @Column(name = "type_name")
    private String typeName;   //电影类型

    @Column(name = "type_id")
    private String typeId;    //电影小类id(默认、喜剧、爱情、动作、恐怖、科幻、剧情、犯罪、奇幻、战争、悬疑、动画、惊悚)

    @Column(name = "is_vip")
    private Integer isVip;  //是否是vip

    @Column(name = "plot")
    private String plot;   //电影描述

    @Column(name = "page_link")
    private String pageLink; //电影详情页面链接地址


    @Column(name = "update_time")
    private String updateTime; //更新时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnDecade() {
        return onDecade;
    }

    public void setOnDecade(String onDecade) {
        this.onDecade = onDecade;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubClassName() {
        return subClassName;
    }

    public void setSubClassName(String subClassName) {
        this.subClassName = subClassName;
    }

    public String getSubClassId() {
        return subClassId;
    }

    public void setSubClassId(String subClassId) {
        this.subClassId = subClassId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}