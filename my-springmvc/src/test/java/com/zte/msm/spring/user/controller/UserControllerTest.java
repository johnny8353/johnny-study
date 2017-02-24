package com.zte.msm.spring.user.controller;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.zte.msm.spring.user.model.UserBO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-config.xml", "classpath*:spring-mvc.xml" })
public class UserControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private UserController controller;
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGet() throws Exception {
		String json="";
		UserBO userBO = new UserBO();
//		json = Jackson
//		this.mockMvc.perform(post("/user/get2.serv", "json")
//				.characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON).content(json.toString()));
		
	}

	@Test
	public void testGet2() {
		fail("Not yet implemented");
	}

}
