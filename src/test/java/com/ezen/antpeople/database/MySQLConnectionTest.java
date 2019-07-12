package com.ezen.antpeople.database;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.dto.TestDTO;
import com.ezen.antpeople.service.TestService;

//JUnit 테스트시 써야할 코드 @RunWith, @ContextConfiguration, @WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(locations= {"classpath:/context-common.xml"})
public class MySQLConnectionTest {
	
	@Resource
	TestService ts;
	
	@Test
	public void testConnection() throws Exception{
		
		   TestDTO test = ts.getTest(1);
		   assertEquals(test.getName(), "김자바");
	
	}

}
