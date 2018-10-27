package com.tdtk.service.impl;

import com.tdtk.mapper.BgmMapper;
import com.tdtk.pojo.Bgm;
import com.tdtk.service.BgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BgmerviceImpl implements BgmService {

	@Autowired
	private BgmMapper bgmMapper;
//	@Autowired
//	private Sid sid;


	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Bgm> queryBgmList() {
		List<Bgm> bgms = bgmMapper.selectAll();
		return bgms;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Bgm queryBgmById(String bgmId) {
		return bgmMapper.selectByPrimaryKey(bgmId);
	}
}

