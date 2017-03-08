package com.zte.msm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

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

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setBasenames(baseName2);
        return messageSource;
    }
}
