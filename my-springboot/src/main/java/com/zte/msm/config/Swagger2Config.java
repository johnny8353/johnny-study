package com.zte.msm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui.html
/**
 *测试：
	http://localhost:8080/your-app-root/v2/api-docs 中your-app-root指的你的wabapp的根路径，
	http://localhost:8080/spring/v2/api-docs
	 再次访问：http://localhost:8080/your-app-root/swagger-ui.html 就可以看到可读性较好的api文档页面。
	 Now you can test it in your browser by visiting http://localhost:8080/your-app-root/swagger-ui.html
	注意：
 * @author JohnnyHuang黄福强
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		//通过注解EnableSwagger2声明Swagger的可用性，此处会定义一个类型为Docket的bean，
//		关于docket类的说明如下：
//		A builder which is intended to be the primary interface into the swagger-springmvc framework.Provides sensible defaults and convenience methods for configuration.
		//Docket的select()方法会提供给swagger-springmvc framework的一个默认构造器（ApiSelectorBuilder），这个构造器为配置swagger提供了一系列的默认属性和便利方法。
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.zte.msm")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		// return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful
		// APIs")
		// .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
		// .termsOfServiceUrl("http://blog.didispace.com/").contact("程序猿DD").version("1.0").build();
		return new ApiInfoBuilder().title("仓库管理API服务").description("更多电动车配件信息,请关注：http://eweiche.com/")
				.termsOfServiceUrl("no terms of service").version("1.0").build();

	}

}
