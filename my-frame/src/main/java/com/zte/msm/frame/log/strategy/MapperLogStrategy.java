package com.zte.msm.frame.log.strategy;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zte.msm.frame.log.EnableLog;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.log.access.dao.LogMapper;
import com.zte.msm.frame.log.access.dao.LogXMapper;
import com.zte.msm.frame.log.access.vo.LogVO;
import com.zte.msm.frame.log.access.vo.LogXVO;
import com.zte.msm.frame.thread.ThreadLocalMap;
import com.zte.msm.frame.util.date.DateUtil;
import com.zte.msm.frame.util.exception.ExceptionUtil;
import com.zte.msm.frame.util.json.JacksonUtil;
/**
 * Dao层的日志控制
 */
@Component("mapperLogStrategy")
public class MapperLogStrategy extends LogStrategy {
	Logger rootLogger = LoggerFactory.getLogger(ServiceLogStrategy.class);

	@Autowired //@Qualifier("xLogMapperImpl")
	private LogMapper logMapper;
	@Autowired //@Qualifier("xLogXMapperImpl")
	private LogXMapper logXMapper;

	public MapperLogStrategy() {
		rootLogger.debug("MapperLogStrategy init...");
	}

	@Override
	public Object handle(ProceedingJoinPoint pjd) throws Throwable {
		rootLogger.debug("MapperLogStrategy handle begin");
		Date beginDate = new Date();

		Object result = null;
		LogVO logVO = new LogVO();
		LogXVO logXVO = new LogXVO();
		Object target = pjd.getTarget();
		Logger logger = LoggerFactory.getLogger(target.getClass());
		String methodName = "";
		StringBuffer inputSB = new StringBuffer();
		
		if(target instanceof LogMapper || target instanceof LogXMapper){//日志类不进行切面，否则死循环
			return pjd.proceed();
		}

		try {
			logXVO.setCreateBy(10209744L);
			logVO.setCreateBy(10209744L);
			logVO.setTable(LOG_MAPPER_TABLE_NAME);
			logXVO.setTable(LOG_MAPPERX_TABLE_NAME);
			logVO.setBeginTime(DateUtil.dateToString(beginDate));
			logVO.setStatus(LOG_STATUS_UNKNOW);
			// 获取linkid
			if(ThreadLocalMap.get(X_LINK_ID)!=null){
				logVO.setLinkId(ThreadLocalMap.get(X_LINK_ID).toString());
			}
			// 获取对应方法上的注解
			Signature signature = pjd.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method targetMethod = methodSignature.getMethod();
			EnableLog enableLog = targetMethod.getAnnotation(EnableLog.class);
			methodName = pjd.getSignature().getName();
			// 记录的内容：用户工号，姓名，日志时间，调用的类和方法，调用说明，异常，调用时间，开始结束时间,输入输出
			logVO.setClassName(substring(target.getClass().getName(), 250));
			logVO.setMethodName(substring(methodName, 250));
			if (enableLog != null) {
				logVO.setLogDesc(substring(enableLog.value(), 250));
			} 
			// 前置通知
			for (Object arg : pjd.getArgs()) {
				inputSB.append(JacksonUtil.toJson(arg));
			}
			logXVO.setInputParams(substring(inputSB.toString(), 3800));
		} catch (Exception e) {
			logger.error("日志初始化报错",e);
		}
		
		try {
			// 执行目标方法
			result = pjd.proceed();
			logVO.setStatus(LOG_STATUS_SUCCESS);
		} catch (Exception e) {
			// 异常通知
			logXVO.setException(substring(ExceptionUtil.getExceptionMessage(e), 3800));
			logVO.setStatus(LOG_STATUS_FAIL);
			throw e;
		} finally {
			logXVO.setOutputParams(substring(JacksonUtil.toJson(result), 3800));
			// 后置通知
			Date endDate = new Date();
			logVO.setEndTime(DateUtil.dateToString(endDate));
			logVO.setSpendTime(DateUtil.getDiffDate(beginDate, endDate));
			// 记录到文件
			logger.info("请求详情日志：{}:{}", logVO.toString(), logXVO.toString());
			// 记录到数据库
			doPersistenceAsync(logMapper,logXMapper,logVO,logXVO);
			rootLogger.debug("MapperLogStrategy handler end");
		}
		return result;
	}

}
