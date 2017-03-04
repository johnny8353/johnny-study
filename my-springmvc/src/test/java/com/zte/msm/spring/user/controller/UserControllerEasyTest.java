package com.zte.msm.spring.user.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zte.msm.frame.base.BaseTest;
import com.zte.msm.frame.common.FormData;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.util.json.JacksonUtil;
import com.zte.msm.spring.user.model.UserBO;

//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@Transactional("transactionManager")  //配置事务管理器
@Rollback(true) //配置事务是否回滚，true表示回滚，false表示不回滚
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
    @ContextConfiguration(name="parent",locations = "classpath:spring-config.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})
public class UserControllerEasyTest extends BaseTest<UserController>{
	private static final Logger logger = LoggerFactory.getLogger(UserControllerEasyTest.class);

	@Test
	public void testGet() throws Exception{
		logger.debug("testGet");
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		perform("/user/get.serv",json);
	}
	
	@Test
	public void testGetList() throws Exception{
		logger.debug("testGetList");
		
		FormData<UserBO> formData = new FormData<>();
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		formData.setBo(userBO);
		formData.setOrder("name");
		String json = JacksonUtil.toJson(formData); // 将对象转换成json
		
		logger.debug("----input:{}",json);
		String responseString = perform("/user/getList.serv",json);
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}
	
	@Test
	public void testGetPage() throws Exception{
		logger.debug("testGetPage");
		FormData<UserBO> formData = new FormData<>();
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		Map<Object,Object> other = new HashMap<Object, Object>();
		other.put("test", "33");
		formData.setPage(2);
		formData.setRows(2);
		formData.setBo(userBO);
		formData.setOrder("name");
		formData.setOther(other);
		String json = JacksonUtil.toJson(formData); // 将对象转换成json
		
		logger.debug("----input:{}",json);
		String responseString = perform("/user/getPage.serv",json);
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}
	
	@Test
	public void testDelete() throws Exception{
		logger.debug("testDelete");
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		
		logger.debug("----input:{}",json);
		String responseString = perform("/user/delete.serv",json);
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}
	
	@Test
	public void testUpdate() throws Exception{
		logger.debug("testUpdate");
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		userBO.setName("黄福强12");
		userBO.setCreateBy(1L);
		userBO.setOrgId(1L);
		userBO.setAccount("1020974433");
		userBO.setEnableFlag("Y");
		userBO.setCreateDate(new Date());
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		
		logger.debug("----input:{}",json);
		String responseString = perform("/user/update.serv",json);
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}
	
	@Test
	public void testInsert() throws Exception{
		logger.debug("testInsert");
		UserBO userBO = new UserBO();
		userBO.setName("黄福强");
		userBO.setCreateBy(1L);
		userBO.setOrgId(1L);
		userBO.setAccount("10209744");
		userBO.setEnableFlag("Y");
		userBO.setCreateDate(new Date());
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		
		logger.debug("----input:{}",json);
		String responseString = perform("/user/insert.serv",json);
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}

}
