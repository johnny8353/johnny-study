package com.zte.msm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

//import com.zte.msm.frame.i18n.HeaderLocaleResolver;

/**
 * Created by Administrator on 2017/2/16.
 */
@Configuration
public class I18NConfig
{
    @Value(value = "${I18NMessage.baseName}")
    private String baseName;
    @Value(value = "${I18NMessage.baseName2}")
    private String baseName2;

    /**
     	<bean id="validatemessageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
		<property name="basenames">
			<list>
				<value>messages/frame_messages</value>
				<value>messages/user_messages</value>
			</list>
		</property>
		<property name="useCodeAsDefaultMessage" value="true" />
		<property name="defaultEncoding" value="UTF-8" />
		<property name="cacheSeconds" value="3600" />
		<!-- 如果在国际化资源文件中找不到对应代码的信息，就用这个代码作为名称 -->
		<!-- <property name="useCodeAsDefaultMessage" value="true" /> -->
		</bean>
     * @return
     */
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		// messageSource.setBasename(baseName); 指定一个文件
		messageSource.setBasenames(baseName, baseName2);
		messageSource.setDefaultEncoding("UTF-8");// 设置编码，否则中文会乱码
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setCacheSeconds(3600);
        return messageSource;
    }
//    @Bean(name = "localeResolver")
//    public HeaderLocaleResolver getLocaleResolver() {
//    	//使用localeResolver 解析 多语言
//    	HeaderLocaleResolver localeResolver = new HeaderLocaleResolver();
//    	return localeResolver;
//    }
//    
    
}
