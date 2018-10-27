package com.tdtk.mapper;

import com.tdtk.pojo.Videos;
import com.tdtk.pojo.vo.VideosVO;
import com.tdtk.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideosMapper extends MyMapper<Videos> {

    /**
     * 查询视频列表
     * @return
     */
    public List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc);

    /**
     * @Description: 查询关注的视频
     */
    public List<VideosVO> queryMyFollowVideos(String userId);

    /**
     * @Description: 查询点赞视频
     */
    public List<VideosVO> queryMyLikeVideos(@Param("userId") String userId);

    /**
     * @Description: 对视频喜欢的数量进行累加
     */
    public void addVideoLikeCount(String videoId);

    /**
     * @Description: 对视频喜欢的数量进行累减
     */
    public void reduceVideoLikeCount(String videoId);

}