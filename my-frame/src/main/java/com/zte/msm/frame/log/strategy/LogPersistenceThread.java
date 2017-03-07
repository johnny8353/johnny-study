package com.zte.msm.frame.log.strategy;

import org.slf4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.log.access.dao.LogMapper;
import com.zte.msm.frame.log.access.dao.LogXMapper;
import com.zte.msm.frame.log.access.vo.LogVO;
import com.zte.msm.frame.log.access.vo.LogXVO;
/**
 * 异步持久化
 * @author JohnnyHuang黄福强
 */
public class LogPersistenceThread extends Thread{
	private Logger logger = LoggerFactory.getLogger(LogPersistenceThread.class);
	
	private LogMapper logMapper;
	private LogXMapper logXMapper;
	private LogVO logVO;
	private LogXVO logXVO;
	public LogPersistenceThread() {
		super();
	}

	public LogPersistenceThread(LogMapper logMapper, LogXMapper logXMapper, LogVO logVO, LogXVO logXVO) {
		super();
		this.logMapper = logMapper;
		this.logXMapper = logXMapper;
		this.logVO = logVO;
		this.logXVO = logXVO;
	}


	//日志不受外部事物影响，开启新的事务
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void run() {
		try {
			logger.debug("LogPersistenceThread doPersistenceAsync begin work");
			logMapper.insert(logVO);
			if (logVO.getId() != null) {
				logXVO.setParentId(logVO.getId());
			}
			logXMapper.insert(logXVO);
			logger.debug("LogPersistenceThread doPersistenceAsync work end");
		} catch (Exception e) {
			logger.error("日志持久化告警：", e);
		}
		
	}
	

}
