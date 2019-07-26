package com.ezen.antpeople.schedule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.ScheService;
import com.ezen.antpeople.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class ScheCRUDTest {
	
	@Resource
	ScheService scheService;
	
	@Resource
	UserService us;	
	
	
	//일정 등록 테스트 - 성공
	@Test @Ignore
	public void saveScheduleTest() {
		Map<String, ScheDetailDTO> testSchedules = new HashMap<String, ScheDetailDTO>();
		UserDetailDTO user = us.findByEmail("tkwkd123@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		testSchedules.put("1", new ScheDetailDTO("123","190705","190705","0100","0200","테스트 제목1",1,5,user));
		testSchedules.put("2", new ScheDetailDTO("124","190705","190705","0200","0300","테스트 제목2",2,6,user));
		testSchedules.put("3", new ScheDetailDTO("125","190705","190705","0300","0400","테스트 제목3",3,7,user));
		testSchedules.put("4", new ScheDetailDTO("126","190705","190705","0400","0500","테스트 제목4",4,8,user));
		
		scheService.saveSchedules(testSchedules);
	}
	
	//일정 가져오기 테스트 - 성공
	@Test 
	public void scheduleListTest() {
		UserDetailDTO user = us.findByEmail("tkwkd123@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		Map<String, ScheDetailDTO> testSchedules = scheService.findAllOnwer(user.getUser_id());
		
		for(String key: testSchedules.keySet()) {
			System.out.println(testSchedules.get(key).toString());
		}
		
	}
	//인원수 채우기 테스트
	
	@Test @Ignore
	public void changeScheduleTest() {
		Map<String, ScheDetailDTO> testSchedules = new HashMap<String, ScheDetailDTO>();
		UserDetailDTO user = us.findByEmail("tkwkd123@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		testSchedules.put("1", new ScheDetailDTO("123","190705","190705","0100","0200","테스트 제목1",1,5,user));
		testSchedules.put("2", new ScheDetailDTO("124","190705","190705","0200","0300","테스트 제목2",2,6,user));
		testSchedules.put("3", new ScheDetailDTO("125","190705","190705","0300","0400","테스트 제목3",3,7,user));
		testSchedules.put("4", new ScheDetailDTO("126","190705","190705","0400","0500","테스트 제목4",4,8,user));
		
		scheService.saveSchedules(testSchedules);
	}

}
