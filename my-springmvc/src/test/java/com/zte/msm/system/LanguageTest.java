package com.zte.msm.system;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.util.json.JacksonUtil;
import com.zte.msm.spring.user.controller.UserController;
import com.zte.msm.spring.user.model.UserBO;

//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources") //1 web资源的位置，默认是src/main/webapp
//@ContextConfiguration(locations = { "classpath*:spring-config.xml", "classpath*:spring-mvc.xml" })
@ContextHierarchy({
    @ContextConfiguration(name="parent",locations = "classpath:spring-config.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})
public class LanguageTest {
	private static final Logger logger = LoggerFactory.getLogger(LanguageTest.class);

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private UserController userController;
	

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		//指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
//		this.mockMvc = MockMvcBuilders.standaloneSetup(this.wac).build();
	}


	
	@Test
	public void testLangEn() throws Exception {
		logger.debug("--testLangEn:");
		UserBO userBO = new UserBO();
		userBO.setName("johnny");
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
		
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/get2.serv", "json")
				//该测试无法通过，在test的环境，localeRelsover 没有被容器识别到，问题未找到原因？
				.header("X-Lang-Id", "en").header("Content-type", "application/json; charset=utf-8")//头信息加上英文标示
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes()))
//				 .andDo(print())         //打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //aop加入 后报错java.lang.AssertionError: Content type not set
		        .andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		logger.debug("----return:{}",responseString);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseString);
		String  reCode = rootNode.path("code").get("msg").textValue();//.replace("RetCode.", "");
		logger.debug("----return:{}",reCode);
		Assert.assertEquals("国际化测试-英文测试出错", "Success", reCode);
	}
	@Test
	public void testLangCN() throws Exception {
		logger.debug("--testLangEn:");
		UserBO userBO = new UserBO();
		userBO.setName("johnny");
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
		
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/get2.serv", "json")
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes()))
//				 .andDo(print())         //打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //aop加入 后报错java.lang.AssertionError: Content type not set
		        .andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		logger.debug("----return:{}",responseString);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseString);
		String  reCode = rootNode.path("code").get("msg").textValue();//.replace("RetCode.", "");
		logger.debug("----return:{}",reCode);
		Assert.assertEquals("国际化测试-中文测试出错", "操作成功", reCode);
	}
	
	public static void main(String[] args) {
		UserBO userBO = new UserBO();
		userBO.setName("johnny");
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
	}

}
