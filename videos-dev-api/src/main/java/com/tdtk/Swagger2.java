package com.tdtk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * 访问地址：http://localhost:8081/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	/**
	 * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	 */
	@Bean
	public Docket createRestApi() {
		
		// 为swagger添加header参数可供输入  
        ParameterBuilder userTokenHeader = new ParameterBuilder();
        ParameterBuilder userIdHeader = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();  
        userTokenHeader.name("headerUserToken").description("userToken")
        	.modelRef(new ModelRef("string")).parameterType("header")
        	.required(false).build();  
        userIdHeader.name("headerUserId").description("userId")
	    	.modelRef(new ModelRef("string")).parameterType("header")
	    	.required(false).build(); 
        pars.add(userTokenHeader.build());
        pars.add(userIdHeader.build());
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.tdtk.controller"))
				.paths(PathSelectors.any()).build()
				.globalOperationParameters(pars);
	}

	/**
	 * @Description: 构建 api文档的信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 设置页面标题
				.title(" 肥猫木屋 后端 API 接口文档")
				// 设置联系人
				.contact(new Contact("肥猫木屋", "http://www.5ifmv.com", "ggyj@163.com"))
				// 描述
				.description("欢迎访问肥猫影视接口文档，这里是描述信息")
				// 定义版本号 ===> 最终版本.修订版本.小版本
				.version("1.0.0").build();
	}

}
