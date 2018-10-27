package com.tdtk.mapper;

import com.tdtk.pojo.Comments;
import com.tdtk.pojo.vo.CommentsVO;
import com.tdtk.utils.MyMapper;

import java.util.List;

public interface CommentsMapperCustom extends MyMapper<Comments> {
	
	public List<CommentsVO> queryComments(String videoId);
}