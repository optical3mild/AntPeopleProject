package com.ezen.antpeople.schedule;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.service.ScheService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class isNotEqualsTest {
	
	@Autowired
	ScheService scheService;
	
	
	//같은 일정이 있는지 확인 테스트 - 테스트 성공
	@Test @Ignore
	public void equalsScheduleIdTest(){
		ScheDetailDTO scheDTO = new ScheDetailDTO(141,"2_19060100001906020000", "190601", "190602", "0000","0000","0__00:00~00:00",0,1,new UserDetailDTO(2,""));
		ScheEntity entity = new ScheEntity(scheDTO);
		boolean a = scheService.equalsScheduleId(entity);
		System.out.println(a);
		assertTrue(a);
	}
	
	//같은 일정에서 필요한 직원수가 같은지 테스트 - 테스트 성공
	@Test @Ignore
	public void equalsScheduleManPowerTest(){
		ScheDetailDTO scheDTO = new ScheDetailDTO(141,"2_19060100001906020000", "190601", "190602", "0000","0000","0__00:00~00:00",0,1,new UserDetailDTO(2,""));
		ScheEntity entity = new ScheEntity(scheDTO);
		boolean a = scheService.equalsScheduleManPower(entity);
		System.out.println(a);
		assertTrue(a);
	}
	
	//일정이 있는 월을 리스트로 출력하는 메소드 테스트 - 테스트 성공
	@Test @Ignore
	public void isScheduleMonthTest() {
		UserDetailDTO user = new UserDetailDTO(2,"");
		Set<String> list = new HashSet<String>(scheService.isScheduleMonthList(user));
		for(String month :list) {
			System.out.println(month.toString());
		}
	}

}
