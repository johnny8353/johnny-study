package com.zte.msm.frame.log.strategy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.log.access.dao.LogMapper;
import com.zte.msm.frame.log.access.dao.LogXMapper;
import com.zte.msm.frame.log.access.vo.LogVO;
import com.zte.msm.frame.log.access.vo.LogXVO;
import com.zte.msm.frame.util.spring.SpringUtil;

public abstract class LogStrategy {
	private Logger logger = LoggerFactory.getLogger(LogStrategy.class);
	public final static String X_LINK_ID = "X_LINK_ID";
	public final static String LOG_STATUS_SUCCESS = "成功";
	public final static String LOG_STATUS_FAIL = "失败";
	public final static String LOG_STATUS_UNKNOW = "未知";
	public final static String LOG_CONTROLLER_TABLE_NAME = "log_controller";
	public final static String LOG_CONTROLLERX_TABLE_NAME = "log_controller_x";
	public final static String LOG_SERVICE_TABLE_NAME = "log_service";
	public final static String LOG_SERVICEX_TABLE_NAME = "log_service_x";
	public final static String LOG_MAPPER_TABLE_NAME = "log_mapper";
	public final static String LOG_MAPPERX_TABLE_NAME = "log_mapper_x";
	public final static String LOG_SQL_SESSION = "sqlSession";//IOC配置bean 日志存储数据库

	public abstract Object handle(ProceedingJoinPoint pjd) throws Throwable;

	public String substring(String value, int beginIndex, int endIndex) {
		if (beginIndex < 0) {
			throw new StringIndexOutOfBoundsException(beginIndex);
		}
		if (endIndex > value.length()) {
			endIndex = value.length();
		}
		int subLen = endIndex - beginIndex;
		if (subLen < 0) {
			throw new StringIndexOutOfBoundsException(subLen);
		}
		return ((beginIndex == 0) && (endIndex == value.length())) ? value : value.substring(beginIndex, endIndex);
	}

	public String substring(String value, int endIndex) {
		return substring(value, 0, endIndex);
	}

	/**
	 * @param logVO
	 * @param logXVO
	 */
	protected void doPersistenceAsync(LogMapper logMapper, LogXMapper logXMapper, LogVO logVO, LogXVO logXVO) {
		try {
			//没有存在sqlSession bean 跳过拦截
			Object sqlSession = null;
			try{
				sqlSession = SpringUtil.getBean(LOG_SQL_SESSION);
			}catch (Exception e) {
				logger.warn("日志持久化告警：没有存在sqlSession bean 不进行日志持久化");
			}
			if(sqlSession != null){
				logger.warn("it exists sqlSession bean , doPersistenceAsync.... ");
				LogPersistenceThread logPersistenceThread = new LogPersistenceThread(logMapper, logXMapper, logVO, logXVO);
				logPersistenceThread.start();
			}
		} catch (Exception e) {
			logger.error("日志持久化告警：", e);
		}
	}
}
