package com.ezen.antpeople.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ezen.antpeople.controller.user.UserController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(locations= {"classpath:/context-common.xml"})
public class LoginTest {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// @Autowired, @Inject, @Resource는 비슷한 
	@Inject
	private WebApplicationContext wac;
	private MockMvc mockmvc;
	
	@Resource
	UserController uc;
	
	@Before
	public void setup() {
		this.mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.debug("setup ControllerTest mockMvc");
	}
	
	@Test
	public void main() throws Exception{
		mockmvc.perform(MockMvcRequestBuilders.get("/")
				)
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void loginCheck() throws Exception{
		this.mockmvc.perform(get("/check")
				.param("id", "admin")
				.param("password", "welcome")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(UserController.class));
	}

	@Test
	public void login() throws Exception{
		mockmvc.perform(MockMvcRequestBuilders.get("/login.do")
				)
		.andDo(print())
		.andExpect(status().isOk());
	}
}
