package com.ezen.antpeople.monthplan;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.sche.MonthPlanDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.MonthPlanService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class MonthPlanCRUTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MonthPlanService ms;
	@Autowired
	UserRepository ur;
	
	//월별 계획 생성 테스트 - 성공
	@Test @Ignore
	public void createMonthPlanTest() {
		ms.newMonthPlan(2, "1907");
	}
	
	//월별 계획 수정 가능 여부 테스트 
	@Test @Ignore
	public void updateMonthPlanTest() {
		ms.stateMonthPlan(2, "1907", true);
	}
	
	//리스트 조회 테스트 - 성공
	@Test @Ignore
	public void monthPlanListTest() {
		UserDetailDTO user = ur.findByEmail("tkwkd123@gmail.com").get().buildDTO();
		List<MonthPlanDTO> planList = ms.monthPlanList(user);
		log.info(planList.toString());
		
	}
	
	

}
