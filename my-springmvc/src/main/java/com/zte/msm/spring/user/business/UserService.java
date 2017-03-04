package com.zte.msm.spring.user.business;



import java.util.List;
import java.util.Map;

import com.zte.msm.frame.base.BaseService;
import com.zte.msm.frame.common.PageRows;
import com.zte.msm.spring.user.model.UserBO;

/**
 *服务接口类 
 */
public interface UserService extends BaseService<UserBO>{
	/**
	 * 通过账户名获得用户信息
	 * 编码作者:TYB
	 * 完成日期:2017年2月25日
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public UserBO getByAccount(String account) throws Exception;
}