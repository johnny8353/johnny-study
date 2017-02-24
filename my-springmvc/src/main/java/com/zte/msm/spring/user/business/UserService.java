package com.zte.msm.spring.user.business;



import com.zte.msm.spring.user.model.UserBO;


/**
 *服务接口类 
 */
public interface UserService
{
	
	public UserBO get(Long id) throws Exception;
	
//	public List<User> getList(Map<String, Object> map, String orderField, String order) throws Exception;
	
	public int delete(Long id) throws Exception;
	
//	public int insert(User entity) throws Exception;
//	
//	public int update(User entity) throws Exception;
//	
//	public PageRows<User> getPage(Map<String, Object> map, String orderField, String order, Long page, Long rows) throws Exception;
	
}