package com.zte.msm.frame.base;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.zte.msm.frame.log.LoggerFactory;
/**
 * 基础测试类
 * @author JohnnyHuang黄福强
 */
@WebAppConfiguration("src/main/resources") //1 web资源的位置，默认是src/main/webapp
public class BaseTest<T> {
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	protected MockMvc mockMvc;
	@Autowired
	private T controller = null;

	@Before
	public void setUp() throws Exception {
		//通过参数指定一组控制器，这样就不需要从上下文获取了；
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	/**
	 * post请求方法封装
	 * @param postUrl 请求的url地址 如perform
	 * @param postParam
	 * @return
	 * @throws Exception
	 */
	protected String perform(String postUrl,String postParam) throws Exception{
		MockHttpServletResponse httpServletResponse = this.mockMvc.perform(post(postUrl,"json")
				.characterEncoding("UTF-8").header("X-Lang-Id", "cn")
				.contentType(MediaType.APPLICATION_JSON)
				.content(postParam.getBytes())
				)
				.andDo(print())//打印出请求和返回的内容
				.andExpect(status().isOk())//返回的状态是200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code.code").value("0000"))//判断服务器返回是否成功，返回值为0000
				.andReturn().getResponse();
		httpServletResponse.setCharacterEncoding("UTF-8");//解决返回值乱码的问题
		String responseString = httpServletResponse.getContentAsString(); // 将相应的数据转换为字符串
		return responseString;
	}
}
