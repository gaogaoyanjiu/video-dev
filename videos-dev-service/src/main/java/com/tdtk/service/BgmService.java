package com.tdtk.service;

import com.tdtk.pojo.Bgm;

import java.util.List;

public interface BgmService {
	
	/**
	 * @Description: 查询背景音乐列表
	 */
	public List<Bgm> queryBgmList();

    Bgm queryBgmById(String bgmId);
}
