package com.zte.msm.frame.log.model;

import com.zte.msm.frame.log.access.vo.LogVO;
import com.zte.msm.frame.log.access.vo.LogXVO;

public class LogBO {
	private LogVO logVO;
	private LogXVO logXVO;
	
	
	public LogVO getLogVO() {
		return logVO;
	}
	public void setLogVO(LogVO logVO) {
		this.logVO = logVO;
	}
	public LogXVO getLogXVO() {
		return logXVO;
	}
	public void setLogXVO(LogXVO logXVO) {
		this.logXVO = logXVO;
	}
	@Override
	public String toString() {
		return "LogBO [" + logVO.toString() + ", " + logXVO.toString() + "]";
	}
	public LogBO(LogVO logVO, LogXVO logXVO) {
		super();
		this.logVO = logVO;
		this.logXVO = logXVO;
	}
	
}
