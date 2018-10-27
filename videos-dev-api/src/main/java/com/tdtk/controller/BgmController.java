package com.tdtk.controller;

import com.tdtk.pojo.Bgm;
import com.tdtk.service.BgmService;
import com.tdtk.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "Bgm列表查询接口",tags = "Bgm列表查询接口")
@RestController
@RequestMapping("/bgm")
public class BgmController {

	@Autowired
	private BgmService bgmService;

	@ApiOperation(value="获取背景音乐", notes="获取背景音乐的接口")
	@PostMapping("/list")
	public JSONResult list() {
		List<Bgm> bgms = bgmService.queryBgmList();
		return JSONResult.ok(bgms);
	}
	
}
