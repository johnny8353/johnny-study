package com.zte.msm.frame.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {  
    private static ApplicationContext applicationContext;  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    @Override  
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {  
        applicationContext = arg0;  
    }  
  
    public static Object getBean(String id) {  
        Object object = null;  
        object = applicationContext.getBean(id);  
        return object;  
    }  
    
   
}  