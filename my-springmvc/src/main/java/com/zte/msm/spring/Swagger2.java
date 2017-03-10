package com.zte.msm.spring;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zte.msm.frame.log.LoggerFactory;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/4/18 下午12:02.
 * @blog http://blog.didispace.com
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class Swagger2 {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(Swagger2.class);
	public Swagger2() {
		// TODO Auto-generated constructor stub
		logger.debug("--Swagger2 init");
	}
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zte.msm"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
                .title("ZTE MSMS RESTful APIs 管理")
                .description("MSMS重构项目")
                .termsOfServiceUrl("http://www.baidu.com/")
                .version("1.0")
                .build();
    }

}
