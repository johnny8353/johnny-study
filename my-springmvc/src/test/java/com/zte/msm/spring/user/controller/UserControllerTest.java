package com.zte.msm.spring.user.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.spring.user.model.UserBO;

//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources") //1 web资源的位置，默认是src/main/webapp
@ContextConfiguration(locations = { "classpath*:spring-config.xml", "classpath*:spring-mvc.xml" })
public class UserControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testGet() throws Exception {
		logger.debug("--testGet:");
		ObjectMapper mapper = new ObjectMapper(); // 转换器
		UserBO userBO = new UserBO();
		userBO.setName("johnny");
		String json = mapper.writeValueAsString(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
		String responseString = this.mockMvc.perform(post("/user/get2.serv", "json")
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes()))
				 .andDo(print())         //打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
//		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//14
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //aop加入 后报错java.lang.AssertionError: Content type not set
//		        .andExpect(content().string(""))
				.andExpect(jsonPath("$.bo.id").isNotEmpty())//id不为空
		        .andExpect(jsonPath("$.bo.id").value(1000))
		        .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
		        ;//15
		logger.debug("----return:{}",responseString);
	}

	@Test
	public void testGet2() {
		fail("Not yet implemented");
	}

}
