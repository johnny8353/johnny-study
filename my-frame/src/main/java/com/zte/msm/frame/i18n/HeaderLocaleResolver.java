package com.zte.msm.frame.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.LocaleResolver;

import com.zte.msm.frame.log.LoggerFactory;

public class HeaderLocaleResolver implements LocaleResolver {
	
	//日志对象
	private static final Logger log = LoggerFactory.getLogger(HeaderLocaleResolver.class);
		
    private Locale locale = null;

    public Locale resolveLocale(HttpServletRequest request) {
		String langId = request.getHeader("X-Lang-Id");
		if("en".equalsIgnoreCase(langId) || "1033".equalsIgnoreCase(langId)){
			this.locale = new Locale("en","US");
		}
		else{
			//缺省设置为中文
			this.locale = new Locale("zh","CN");
		}
    	
    	log.info(" 获取LOCALE:" + this.locale.toString());
    	
        return this.locale;
    } 

    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    	this.locale = locale;
    }
  
}
