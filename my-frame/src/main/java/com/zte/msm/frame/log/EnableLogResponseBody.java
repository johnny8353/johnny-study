package com.zte.msm.frame.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 组合注解
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableLog
@ResponseBody
public @interface EnableLogResponseBody {
	/*
	 * 说明
	 */
	String desc() default "无";  
}
