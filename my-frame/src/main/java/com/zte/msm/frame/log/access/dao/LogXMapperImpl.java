package com.zte.msm.frame.log.access.dao;

import org.springframework.stereotype.Repository;

import com.zte.msm.frame.base.BaseMapperImpl;
import com.zte.msm.frame.log.access.vo.LogXVO;

@Repository("xLogXMapperImpl")
public class LogXMapperImpl extends BaseMapperImpl<LogXVO> implements LogXMapper{
	public String getCanonicalName() {
		return LogXMapper.class.getCanonicalName();
	}
}
