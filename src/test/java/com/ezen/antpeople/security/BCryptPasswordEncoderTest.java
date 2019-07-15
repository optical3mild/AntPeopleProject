package com.ezen.antpeople.security;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(locations= {"classpath:/context-common.xml"})
public class BCryptPasswordEncoderTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource 
	private BCryptPasswordEncoder pe;

	@Test @Ignore
	public void testPasswordEncoder() throws Exception {
		
		//패스워드 생성
		String password1 = pe.encode("password1");
		String password2 = pe.encode("java123");
		String password3 = pe.encode("spring123");
		
		//패스워드 생성 결과물
		log.info("returnPassword1 : " + password1);
		log.info("returnPassword2 : " + password2);
		log.info("returnPassword3 : " + password3);
		
		//패스워드 확인
		assertTrue(pe.matches("password1", password1));
		assertTrue(pe.matches("java123", password2));
		assertTrue(pe.matches("spring123", password3));
		
	}
}
