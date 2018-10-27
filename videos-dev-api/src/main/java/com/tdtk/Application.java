package com.tdtk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages="com.tdtk.mapper")
@ComponentScan(basePackages = {"com.tdtk"})
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//为了打包springboot项目
//	@Override
//	protected SpringApplicationBuilder configure(
//			SpringApplicationBuilder builder) {
//		return builder.sources(this.getClass());
//	}
	
}
