package com.zte.msm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * http://localhost:8080/spring/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	//通过注解EnableSwagger2声明Swagger的可用性，此处会定义一个类型为Docket的bean，
	//	关于docket类的说明如下：
	//	A builder which is intended to be the primary interface into the swagger-springmvc framework.Provides sensible defaults and convenience methods for configuration.
	//Docket的select()方法会提供给swagger-springmvc framework的一个默认构造器（ApiSelectorBuilder），这个构造器为配置swagger提供了一系列的默认属性和便利方法。
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
//        .title("Spring Boot中使用Swagger2构建RESTful APIs")
//        .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
//        .termsOfServiceUrl("http://blog.didispace.com/")
//        .contact("程序猿DD")
//        .version("1.0")
//        .build();
    }

}
