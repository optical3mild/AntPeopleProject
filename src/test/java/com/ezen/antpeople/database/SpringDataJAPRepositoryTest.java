package com.ezen.antpeople.database;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(locations= {"classpath:/context-common.xml"})
public class SpringDataJAPRepositoryTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	UserService us; 
	
	//findOneByName
	@Test @Ignore
	public void SaveUserTest() throws Exception {
		//임시 유저 데이터
		UserDTO user = new UserDTO("java123@gmail.com","java123","김자바","1","대전광역시 서구","010-1123-4324");
		
		//데이터 삽입
		us.saveUser(user);
		
		//데이터 확인
		UserEntity userFind =us.findUserByEmail("java123@gmail.com");
		System.out.println(userFind.toString());
	}
	
	@Test 
	public void getUserTest() throws Exception {
		//데이터 확인
		log.info("메소드 시작");
		UserEntity userFind = us.findUserByEmail("java123@gmail.com");
		System.out.println(userFind.toString());
		//assertEquals("김자바", userFind.getName());
		log.info("메소드 종료");
	}
	
	//

}
