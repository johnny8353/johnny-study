package com.zte.msm.frame.log;


import java.lang.annotation.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.zte.msm.frame.base.BaseController;
import com.zte.msm.frame.base.BaseMapperImpl;
import com.zte.msm.frame.base.BaseService;
import com.zte.msm.frame.log.strategy.LogStrategy;
import com.zte.msm.frame.util.spring.SpringUtil;

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
		loggerRoot.debug("--Log Aspect init...");
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
		Object target = pjd.getTarget();
		LogStrategy logStrategy = null;
		
		//这是获得某类所有注解的方法：
		Annotation[] annotations = target.getClass().getAnnotations();
        for(Annotation annotation : annotations){
            //判斷當前注解對象是否為自定義注解
            if(annotation.annotationType() == Controller.class||annotation.annotationType() == ControllerAdvice.class
            		||target instanceof BaseController){
                logStrategy = (LogStrategy) SpringUtil.getBean("controllerLogStrategy");
            }
            else if(annotation.annotationType() == Service.class||target instanceof BaseService){
                logStrategy = (LogStrategy) SpringUtil.getBean("serviceLogStrategy");
            }
            else if((annotation.annotationType() == Repository.class||target instanceof BaseMapperImpl)){
            	logStrategy = (LogStrategy) SpringUtil.getBean("mapperLogStrategy");
            }
        }
		Object result = logStrategy.handle(pjd);
		return result;
	}
}
