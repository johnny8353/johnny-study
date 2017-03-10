package com.zte.msm.spring.user.access.dao;

import org.springframework.stereotype.Repository;

import com.zte.msm.frame.base.BaseMapperImpl;
import com.zte.msm.spring.user.access.vo.UserVO;
@Repository("xUserMapperImpl")
public class UserMapperImpl extends BaseMapperImpl<UserVO> implements UserMapper{

	@Override
	public String getCanonicalName() {
		return UserMapper.class.getCanonicalName();
	}

	@Override
	public UserVO getByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}
	
}