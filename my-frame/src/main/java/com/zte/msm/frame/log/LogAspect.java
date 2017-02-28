package com.zte.msm.frame.log;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.zte.msm.frame.base.BaseBO;
import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.frame.util.date.DateUtil;
import com.zte.msm.frame.util.http.HttpUtil;
import com.zte.msm.frame.util.json.JacksonUtil;

/**
 * 日志切面，在需要日志的handler加上EnableLog注解
 */
//Spring中的切面类固然要用@Aspect标注，但也不要忘了用@Componet标注，这样才能被注册到容器中
@Order(2)//值越小, 优先级越高.
@Aspect
@Component("logAspect")
public class LogAspect {
	Logger loggerRoot = LoggerFactory.getLogger(LogAspect.class);
	public LogAspect(){
		loggerRoot.debug("Log Aspect int...");
	}

	/**
	 * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码. 
	 * 使用 @Pointcut 来声明切入点表达式. 
	 * 后面的其他通知直接使用方法名来引用当前的切入点表达式. 
	 *@annotation(com.zte.msm.frame.log.EnableLog)  || @annotation(com.zte.msm.frame.log.EnableLogResponseBody)
	 */
	@Pointcut("@annotation(com.zte.msm.frame.log.EnableLog) ")
	public void declareJointPointExpression(){}
	
	/**
	 * 环绕通知需要携带 ProceedingJoinPoint 类型的参数. 
	 * 环绕通知类似于动态代理的全过程: ProceedingJoinPoint 类型的参数可以决定是否执行目标方法.
	 * 且环绕通知必须有返回值, 返回值即为目标方法的返回值 ;
	 * 
	 * 环绕通知是所有通知类型中功能最为强大的, 能够全面地控制连接点. 甚至可以控制是否执行连接点.
	 * 对于环绕通知来说, 连接点的参数类型必须是 ProceedingJoinPoint . 它是 JoinPoint 的子接口, 允许控制何时执行, 是否执行连接点.
	 * 在环绕通知中需要明确调用 ProceedingJoinPoint 的 proceed() 方法来执行被代理的方法. 
	 * 如果忘记这样做就会导致通知被执行了, 但目标方法没有被执行.
	 * 注意: 环绕通知的方法需要返回目标方法执行之后的结果, 即调用 joinPoint.proceed(); 的返回值, 否则会出现空指针异常
	 * @throws Throwable 
	 */
	@Around("declareJointPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable{
		loggerRoot.debug("-LogAspect begin work");
		HttpServletRequest hsr = null;
		ServiceData resultSD = null;
		Date startDt = new Date();
		// 在后台中输出错误异常异常信息，通过log4j输出。
		Object target = pjd.getTarget();
		Logger logger = LoggerFactory.getLogger(target.getClass());
System.out.println(target.getClass().getName());
		
		//获取对应方法上的注解
		Signature signature = pjd.getSignature();    
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();
		EnableLog enableLog = targetMethod.getAnnotation(EnableLog.class);
		if(enableLog!=null){
logger.debug(enableLog.desc());
		}
		Object result = null;
		String methodName = pjd.getSignature().getName();
		//记录到文件  记录到数据库
		//记录的内容：用户工号，姓名，日志时间，调用的类和方法，调用说明，异常，调用时间，开始结束时间,输入输出
		try {
			//前置通知
			logger.debug("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			for (Object arg : pjd.getArgs()) {
				if(arg instanceof BaseBO){
logger.debug(JacksonUtil.toJson(arg)); //					
				}
				if (arg instanceof HttpServletRequest) {
					hsr = (HttpServletRequest) arg;
logger.debug(arg.getClass().toString()); //
logger.debug(HttpUtil.getClientIpAddr(hsr)); //
logger.debug(HttpUtil.getServerIpAddr(hsr)); //
logger.debug(HttpUtil.getHeadInfo(hsr));
logger.debug(hsr.getRequestURL().toString());
logger.debug(hsr.getRequestURI());
				}
			}
			//执行目标方法
			result = pjd.proceed();
			if(hsr!=null && result instanceof ServiceData ){
				resultSD = (ServiceData)result;
			}
			//返回通知
			logger.debug("The method " + methodName + " ends with " + result);
logger.debug(JacksonUtil.toJson(result)); //map转json  
		} catch (Throwable e) {
			//异常通知
			logger.debug("The method " + methodName + " occurs exception:" + e);
			throw e;
		}
		//后置通知
		logger.debug("The method " + methodName + " ends");
//		logger.debug("userId:{},userName:{},createDate:{},className:{},methodName:{},exceptionCode:{},spendTime:{}",
//				userId,userName,createDate,className,methodName);
//		logger.debug("desc:{},exception:{},inputArgs:{},outputArgs:{}");
		Date endDt = new Date();
logger.debug(DateUtil.getDiffDate(startDt, endDt));
		return result;
	}
}
