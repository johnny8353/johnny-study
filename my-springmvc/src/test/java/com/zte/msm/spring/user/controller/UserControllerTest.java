package com.zte.msm.spring.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zte.msm.frame.common.FormData;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.util.json.JacksonUtil;
import com.zte.msm.spring.user.model.UserBO;

//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@Transactional("transactionManager")  //配置事务管理器
@Rollback(true) //配置事务是否回滚，true表示回滚，false表示不回滚
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources") //1 web资源的位置，默认是src/main/webapp
//@ContextConfiguration(locations = { "classpath*:spring-config.xml", "classpath*:spring-mvc.xml" })
@ContextHierarchy({
    @ContextConfiguration(name="parent",locations = "classpath:spring-config.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})
public class UserControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		//指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
//		this.mockMvc = MockMvcBuilders.standaloneSetup(this.wac).build();
	}
	
	@Test
	public void testGet() throws Exception{
		logger.debug("testGet");
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/get.serv","json")
				.characterEncoding("UTF-8").header("X-Lang-Id", "en")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}
	
	
	@Test
	public void testGetList() throws Exception{
		logger.debug("testGetList");
		FormData<UserBO> formData = new FormData<>();
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		Map<Object,Object> other = new HashMap<Object, Object>();
		other.put("test", "33");
		formData.setBo(userBO);
		formData.setOrder("name");
		formData.setOther(other);
		String json = JacksonUtil.toJson(formData); // 将对象转换成json
		logger.debug("----input:{}",json);
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/getList.serv","json")
				.characterEncoding("UTF-8").header("X-Lang-Id", "cn")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
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
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/getPage.serv","json")
				.characterEncoding("UTF-8").header("X-Lang-Id", "cn")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}
	
	@Test
	public void testDelete() throws Exception{
		logger.debug("testDelete");
		UserBO userBO = new UserBO();
		userBO.setId(100003L);
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/delete.serv","json")
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
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
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/update.serv","json")
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
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
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/insert.serv","json")
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		logger.debug("----return:{}",responseString);//输出 服务器返回信息
	}

	@Test
	public void testGet2() throws Exception {
		logger.debug("--testGet2:");
		UserBO userBO = new UserBO();
		userBO.setName("johnny");
		userBO.setCreateDate(new Date());
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
		
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post("/user/get2.serv", "json")
				.characterEncoding("UTF-8")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.getBytes()))
				 .andDo(print())         //打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
//		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//14
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //aop加入 后报错java.lang.AssertionError: Content type not set
//		        .andExpect(content().string(""))
				.andExpect(jsonPath("$.bo.id").isNotEmpty())//id不为空
		        .andExpect(jsonPath("$.code.code").value("0000"))//返回值为0000
//		        .andExpect(jsonPath("$.code.msg").value("操作成功"))//返回也存在中文乱码问题 ，需要处理
		        .andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		logger.debug("----return:{}",responseString);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseString);
		String  reCode = rootNode.path("code").get("msg").textValue();//.replace("RetCode.", "");
		logger.debug("----return:{}",reCode);
		JsonNode boNode = rootNode.path("bo");
		JsonNode otherNode = rootNode.path("other");
	}
	
	public static void main(String[] args) {
		UserBO userBO = new UserBO();
		userBO.setName("johnny");
		String json = JacksonUtil.toJson(userBO); // 将对象转换成json
		logger.debug("----input:{}",json);
	}

}
