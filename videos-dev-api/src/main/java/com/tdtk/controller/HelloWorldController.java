package com.tdtk.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Api(value = "测试Springboot的接口",tags = "测试Springboot的Controller")
@Controller
public class HelloWorldController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String Hello() {
		return "Hello Spring Boot~";
	}

	@GetMapping("/index")
	public String index() {
		return "pages/index";
	}
	
}
