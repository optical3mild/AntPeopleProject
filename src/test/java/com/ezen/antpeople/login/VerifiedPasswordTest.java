package com.ezen.antpeople.login;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes=RootConfig.class)
public class VerifiedPasswordTest {
	
	@Resource
	UserService us;
	
	@Test @Ignore
	
	public void VerifyPasswordTest() {
		
	}
	

}
