package com.zte.msm.spring.user.access.dao;

import com.zte.msm.frame.base.BaseMapper;
import com.zte.msm.spring.user.access.vo.UserVO;

public interface UserMapper extends BaseMapper<UserVO>{
    UserVO getByAccount(String account);
}