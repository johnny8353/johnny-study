package com.zte.msm.frame.log.strategy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zte.msm.frame.base.BaseController;
import com.zte.msm.frame.common.ServiceData;
import com.zte.msm.frame.log.EnableLog;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.log.access.dao.LogMapper;
import com.zte.msm.frame.log.access.dao.LogXMapper;
import com.zte.msm.frame.log.access.vo.LogVO;
import com.zte.msm.frame.log.access.vo.LogXVO;
import com.zte.msm.frame.thread.ThreadLocalMap;
import com.zte.msm.frame.util.date.DateUtil;
import com.zte.msm.frame.util.exception.ExceptionUtil;
import com.zte.msm.frame.util.http.HttpUtil;
import com.zte.msm.frame.util.json.JacksonUtil;
import com.zte.msm.frame.util.string.StringUtil;

/**
 * 控制层的日志控制
 * @author JohnnyHuang黄福强
 */
@Component("controllerLogStrategy")
public class ControllerLogStrategy extends LogStrategy {
	protected ServiceData.RetCode SUCCESS = ServiceData.RetCode.Success;
	protected ServiceData.RetCode AUTH_FAILED = ServiceData.RetCode.AuthFailed;
	protected ServiceData.RetCode PERMISSON_DENIED = ServiceData.RetCode.PermissionDenied;
	protected ServiceData.RetCode VALIDATION_ERROR = ServiceData.RetCode.ValidationError;
	protected ServiceData.RetCode BUSINESS_ERROR = ServiceData.RetCode.BusinessError;
	protected ServiceData.RetCode SERVER_ERROR = ServiceData.RetCode.ServerError;
	
	Logger rootLogger = LoggerFactory.getLogger(ControllerLogStrategy.class);

	@Autowired //@Qualifier("xLogMapperImpl")
	private LogMapper logMapper;
	@Autowired //@Qualifier("xLogXMapperImpl")
	private LogXMapper logXMapper;

	public ControllerLogStrategy() {
		rootLogger.debug("ControllerLogStrategy init...");
	}

	@Override
	public Object handle(ProceedingJoinPoint pjd) throws Throwable {
		rootLogger.debug("ControllerLogStrategy handle begin");
		Date beginDate = new Date();

		HttpServletRequest hsr = null;
		Object result = null;
		LogVO logVO = new LogVO();
		LogXVO logXVO = new LogXVO();
		BaseController target = (BaseController) pjd.getTarget();
		Logger logger = LoggerFactory.getLogger(target.getClass());
		StringBuffer inputParamsSB = new StringBuffer();
		String methodName = "";
		ExceptionHandler exceptionHandler = null;
		try {
			logXVO.setCreateBy(10209744L);
			logVO.setCreateBy(10209744L);
			logVO.setTable(LOG_CONTROLLER_TABLE_NAME);
			logXVO.setTable(LOG_CONTROLLERX_TABLE_NAME);
			logVO.setBeginTime(DateUtil.dateToString(beginDate));
			logVO.setStatus(LOG_STATUS_UNKNOW);
			// 获取linkid
			ThreadLocalMap.put(X_LINK_ID, StringUtil.GetGUID());
			logVO.setLinkId(ThreadLocalMap.get(X_LINK_ID).toString());
			
			// 获取对应方法上的注解
			Signature signature = pjd.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method targetMethod = methodSignature.getMethod();
			EnableLog enableLog = targetMethod.getAnnotation(EnableLog.class);
			exceptionHandler = targetMethod.getAnnotation(ExceptionHandler.class);//是否异常方法
			methodName = pjd.getSignature().getName();
			// 记录的内容：用户工号，姓名，日志时间，调用的类和方法，调用说明，异常，调用时间，开始结束时间,输入输出
			logVO.setClassName(substring(target.getClass().getName(), 250));
			logVO.setMethodName(substring(methodName, 250));
			if (enableLog != null) {
				logVO.setLogDesc(substring(enableLog.value(), 250));
			} 
			// 前置通知
			logger.debug("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			for (Object arg : pjd.getArgs()) {
				if (arg instanceof HttpServletRequest) {
					hsr = (HttpServletRequest) arg;
					logVO.setHsrClassName(substring(arg.getClass().toString(), 250));
					logVO.setClientInfo(substring(HttpUtil.getClientIpAddr(hsr), 250));
					logVO.setServerInfo(substring(HttpUtil.getServerIpAddr(hsr), 250));
					logVO.setRequestHead(substring(HttpUtil.getHeadInfo(hsr), 980));
					logVO.setRequestUrl(substring(hsr.getRequestURI(), 250));
					logVO.setRequestFullUrl(substring(hsr.getRequestURL().toString(), 250));
				}else{
					//处理BindingResult tojson报错，直接跳过
					if(arg instanceof BindingResult) break;
					inputParamsSB.append(JacksonUtil.toJson(arg));
				}
			}
			logXVO.setInputParams(substring(inputParamsSB.toString(), 3800));
		} catch (Exception e) {
			logger.error("日志初始化报错",e);
		}
		
		try {
			// 执行目标方法
			result = pjd.proceed();
			// 返回通知
			logger.debug("The method " + methodName + " ends with " + result);
			logVO.setStatus(LOG_STATUS_SUCCESS);
			//异常方法拦截
			if(exceptionHandler!=null){
				logVO.setStatus(LOG_STATUS_FAIL);
			}
		} catch (Exception e) {
			// 异常通知
			logger.debug("The method " + methodName + " occurs exception:" + e);
			logXVO.setException(substring(ExceptionUtil.getExceptionMessage(e), 3800));
			logVO.setStatus(LOG_STATUS_FAIL);

			// 异常统一在ExceptionHandler.handler处理
			if (hsr != null && result == null) {
				result = com.zte.msm.frame.exception.ExceptionHandler.handler(target, hsr, e);
			}
		} finally {
			logXVO.setOutputParams(substring(JacksonUtil.toJson(result), 3800));
			// 后置通知
			logger.debug("The method " + methodName + " ends");
			Date endDate = new Date();
			logVO.setEndTime(DateUtil.dateToString(endDate));
			logVO.setSpendTime(DateUtil.getDiffDate(beginDate, endDate));
			// 记录到文件
			logger.info("请求详情日志：{}:{}", logVO.toString(), logXVO.toString());
			// 记录到数据库
			doPersistenceAsync(logMapper,logXMapper,logVO,logXVO);
			rootLogger.debug("ControllerLogStrategy handler end");
		}
		return result;
	}
	
	public String getInputParams(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		// 去掉最后一个空格
		return queryString.toString();
	}

}
