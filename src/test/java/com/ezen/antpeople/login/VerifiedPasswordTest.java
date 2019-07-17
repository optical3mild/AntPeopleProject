package com.ezen.antpeople.login;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.dto.user.UserDTO;
import com.ezen.antpeople.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(locations= {"classpath:/context-common.xml"})
public class VerifiedPasswordTest {
	
	@Resource
	UserService us;
	
	@Test 
	
	public void VerifyPasswordTest() {
		UserDTO user = new UserDTO("java333@gmail.com","java333","김자바2","1","서울특별시","010-5675-6666",1);
		
		assertTrue(us.verifyPassword(user));
		
	}
	

}
