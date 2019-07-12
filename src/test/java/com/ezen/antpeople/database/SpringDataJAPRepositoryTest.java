package com.ezen.antpeople.database;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(locations= {"classpath:/context-common.xml"})
public class SpringDataJAPRepositoryTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	UserService us; 
	
	//SaveUserTest - 유저 정보 저장(회원가입)
	@Test@Ignore
	public void SaveUserTest() throws Exception {
		//임시 유저 데이터
		UserDTO user = new UserDTO("java1234@gmail.com","java1234","김자바2","1","대전광역시 서구","010-1123-4324");
		
		//데이터 삽입
		us.saveUser(user);
		
		//데이터 확인
		UserDTO userFind =us.findUserByEmail("java1234@gmail.com");
		System.out.println(userFind.toString());
	}
	
	//getUserTest - 유저정보 확인 
	@Test @Ignore
	public void getUserTest() throws Exception {
		//데이터 확인
		UserDTO userFind = us.getUser(1);
		UserDTO userFind2 = us.findUserByEmail("java123@gmail.com");		
		System.out.println(userFind.toString());
		System.out.println(userFind2.toString());
		//assertEquals("김자바", userFind.getName());
	}
	
	//deleteUserTest - 유저 정보 삭제
	@Test @Ignore
	public void deleteUserTest() throws Exception{
		//임시 유저 데이터 (실제로는 이메일 정보만 쓰임, 나머지는 더미데이터)
		UserDTO user = new UserDTO("java1234@gmail.com","1","1","1","1","1");
		us.deleteUser(user);
		log.info("삭제 완료");
		
	}
	
	//updateuserTest - 유저의 주소,전화번호 변경
	@Test 
	public void updateUserTest() throws Exception{
		//임시 유저 데이터 (주소, 전화번호 부분만 변경)
		UserDTO user = new UserDTO("java1234@gmail.com","java1234","김자바2","1","서울특별시","010-5675-6666");
		
		//데이터 변경
		us.updateUser(user);
		
		//데이터 확인
		UserDTO userFind =us.findUserByEmail("java1234@gmail.com");
		System.out.println(userFind.toString());
		
	}

}
