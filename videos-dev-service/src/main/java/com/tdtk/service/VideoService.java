package com.tdtk.service;

import com.tdtk.pojo.Comments;
import com.tdtk.pojo.Videos;
import com.tdtk.utils.PagedResult;

import java.util.List;

public interface VideoService {

    /**
     * 保存视频
     */
    public String saveVideo(Videos video);

    /**
     * 修改视频封面
     */
    public void updateVideo(String videoId, String coverPath);

    /**
     * 分页查询视频列表
     */
    public PagedResult getAllVideos(Videos video,Integer isSaveRecord,Integer page,Integer pageSize);

    /**
     * 分页查询视频列表
     */
    public List<String> getHotWords();

    public void saveComment(Comments comment);
    /**
     * @Description: 留言分页
     */
    public PagedResult getAllComments(String videoId, Integer page, Integer pageSize);

    /**
     * @Description: 用户喜欢/点赞视频
     */
    public void userLikeVideo(String userId, String videoId, String videoCreaterId);

    /**
     * @Description: 用户不喜欢/取消点赞视频
     */
    public void userUnLikeVideo(String userId, String videoId, String videoCreaterId);

    /**
     * @Description: 查询我喜欢的视频列表
     */
    public PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize);

    /**
     * @Description: 查询我关注的人的视频列表
     */
    public PagedResult queryMyFollowVideos(String userId, Integer page, Integer pageSize);




}
