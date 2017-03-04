package com.zte.msm.frame.log.access.dao;

import org.springframework.stereotype.Repository;

import com.zte.msm.frame.base.BaseMapperImpl;
import com.zte.msm.frame.log.access.vo.LogVO;

@Repository("xLogMapperImpl")
public class LogMapperImpl extends BaseMapperImpl<LogVO> implements LogMapper {
	public String getCanonicalName() {
		return LogMapper.class.getCanonicalName();
	}

}
