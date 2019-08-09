package com.ezen.antpeople.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserDTO;
import com.ezen.antpeople.dto.sche.ScheUserListDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.ScheService;
import com.ezen.antpeople.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class ScheCRUDTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	ScheService scheService;
	
	@Resource
	UserService us;	
	
	
	//일정 등록 테스트 - 성공
	@Test @Ignore
	public void saveScheduleTest() {
		Map<String, ScheDetailDTO> testSchedules = new HashMap<String, ScheDetailDTO>();
		UserDetailDTO user = new UserDetailDTO(2,"");//이메일은 DB에 따라 변경해야함!!
		List<UserDetailDTO> users = new ArrayList<UserDetailDTO>();
		users.add(new UserDetailDTO(3,""));
		users.add(new UserDetailDTO(5,""));
		testSchedules.put("1", new ScheDetailDTO("6666666666","190705","190705","0100","0200","테스트 제목1",0,5,user,users));
		testSchedules.put("2", new ScheDetailDTO("4445555555","190705","190705","0200","0300","테스트 제목2",0,5,user));
		testSchedules.put("3", new ScheDetailDTO("6664444444","190705","190705","0300","0400","테스트 제목3",0,5,user,users));
		testSchedules.put("4", new ScheDetailDTO("5553333333","190705","190705","0400","0500","테스트 제목4",0,5,user,users));
		
		scheService.saveSchedules(testSchedules);
	}

	//일정 가져오기 사장 테스트 - 성공
	@Test @Ignore
	public void scheduleListOwnerTest() {
		UserDetailDTO user = us.findByEmail("tkwkd123@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		Set<ScheDetailDTO> testSchedules = new HashSet<ScheDetailDTO>(scheService.findAllOnwer(user.getUser_id()));
		log.info(testSchedules.toString());
	}
	//일정 가져오기 (직원신청가능) 테스트 - 성공
	@Test @Ignore
	public void scheduleListStaffTest() {
		UserDetailDTO user = us.findByEmail("wlrdnjs111@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		Set<ScheDetailDTO> testSchedules = new HashSet<ScheDetailDTO>(scheService.findAllStaff(user,"1908"));
		log.info(testSchedules.toString());
	}
	//일정 가져오기 직원 테스트 
		@Test @Ignore
		public void StaffApplyTest() {
			UserDetailDTO user = us.findByEmail("wlrdnjs111@gmail.com"); //이메일은 DB에 따라 변경해야함!!
			Set<ScheDetailDTO> testSchedules = new HashSet<ScheDetailDTO>(scheService.staffApply(user,"1907"));
			for(ScheDetailDTO schedule : testSchedules) {
				log.info(schedule.toString());
			}
		}
	
	//월별 일정 가져오기 테스트 - 성공
	@Test @Ignore
	public void scheduleMonthListTest() {
		UserDetailDTO user = us.findByEmail("tkwkd123@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		log.info("월별 일정 유저 ID : " + user.getUser_id());
		ScheUserListDTO testSchedules = scheService.findAllMonthAndUser(user,"1907");
		log.info("월별 일정 가져오기 테스트 결과 : "+ testSchedules.toString());
	}
	
	//월별 직원 일정 가져오기 테스트 - 성공
	@Test 
	public void scheduleMonthStaffListTest() {
		UserDetailDTO user = us.findByEmail("wlrdnjs111@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		log.info("직원 ID : " + user.getUser_id());
		ScheUserListDTO testSchedules = scheService.findAllMonthAndStaff(user,"1908");
		log.info("월별 일정 가져오기 테스트 결과 : "+ testSchedules.toStringStaff());
	}
	
	//오늘 근무 직원 리스트 가져오기 테스트
	@Test 
	public void todayStaffListTest() {
		UserDetailDTO user = us.findByEmail("wlrdnjs101@gmail.com"); //이메일은 DB에 따라 변경해야함!!
		log.info("직원 ID : " + user.getUser_id());
		List<ScheUserDTO> testSchedules = scheService.todayStaffList("둔산지점","190810");
		log.info("당일 근무하는 직원 리스트 : "+ testSchedules.toString());
	}
	//일정 수정 및 삭제 테스트 - 성공
	@Test @Ignore
	public void updateScheduleTest() {
		Map<String, ScheDetailDTO> testSchedules = new HashMap<String, ScheDetailDTO>();
		UserDetailDTO user = new UserDetailDTO(2,"");
		List<UserDetailDTO> users = new ArrayList<UserDetailDTO>();
		users.add(new UserDetailDTO(3,""));
		users.add(new UserDetailDTO(4,""));
		//testSchedules.put("1", new ScheDetailDTO("6666666666","190705","190705","0100","0200","테스트 제목1",-1,10,user)); // 일정 삭제
		testSchedules.put("2", new ScheDetailDTO("4445555555","190705","190705","0200","0300","테스트 제목2",0,10,user)); // 일정 변경
		testSchedules.put("3", new ScheDetailDTO("6664444444","190705","190705","0300","0400","테스트 제목3",0,15,user)); // 일정 변경
		testSchedules.put("4", new ScheDetailDTO("5553333333","190705","190705","0400","0500","테스트 제목4",0,5,user)); // 일정 유지
		scheService.updateSchedule(testSchedules);
	}
	
	//근무 신청 테스트 - 성공
	@Test @Ignore
	public void updatePeopleCountAndUsersTest() {
		UserDetailDTO user = new UserDetailDTO(3,"");
		scheService.updateUserSchedule(user,"6666666666");
	}
	
	//일정 승인, 거절 테스트
	@Test @Ignore
	public void permissionScheduleTest() {
		Map<Integer,Set<String>> map = new HashMap<Integer,Set<String>>();
		Set<String> set1 = new HashSet<String>();
		//set1.add("5553333333");
		Set<String> set2 = new HashSet<String>();
		set2.add("5553333333");
		set2.add("6664444444");
		map.put(3, set1);
		log.info("삭제 목록 : " + map);
		scheService.permissionSchedule(map, "1907");
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
