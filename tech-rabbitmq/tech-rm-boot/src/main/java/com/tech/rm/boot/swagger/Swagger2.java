package com.tech.rm.boot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	   // http://127.0.0.1:8080/swagger-ui.html
	   @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.tech.rm.boot"))
	                .paths(PathSelectors.any())
	                .build();
	    }
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("springBoot集成rabbitmq")
	                .description("测试SpringBoot整合进行各种工作模式信息的发送")
/*
	                .termsOfServiceUrl("https://www.jianshu.com/p/c79f6a14f6c9")
*/
	                .contact("lw")
	                .version("1.0")
	                .build();
	    }
}
