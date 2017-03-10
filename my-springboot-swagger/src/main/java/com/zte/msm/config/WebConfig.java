package com.zte.msm.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import com.zte.msm.frame.common.HttpCryptoFilter;

/**
 * 当使用Spring Boot来架设服务系统时，有时候也需要用到前端页面，当然就不可或缺地需要访问其他一些静态资源，比如图片、css、js等文件。
 * 那么如何设置Spring Boot网站可以访问得到这些静态资源，以及静态资源如何布局？
 * 
 * @author JohnnyHuang黄福强
 *
 */
@Configuration
//@EnableWebMvc
//@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**").addResourceLocations("/");
//	}
//
//	@Bean
//	public FilterRegistrationBean cryptoFilterRegistrationBean() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setName("HttpCryptoFilter");
//		HttpCryptoFilter HttpCryptoFilter = new HttpCryptoFilter();
//		registrationBean.setFilter(HttpCryptoFilter);
//		registrationBean.setOrder(1);
//		List<String> urlList = new ArrayList<String>();
//		urlList.add("*.do");
//		urlList.add("*.serv");
//		urlList.add("/configuration*");
//		urlList.add("/v2/api-docs");
//		urlList.add("/swagger-resources");
//		registrationBean.setUrlPatterns(urlList);
//		return registrationBean;
//	}
}