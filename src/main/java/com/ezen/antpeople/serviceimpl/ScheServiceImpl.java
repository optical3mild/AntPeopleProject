package com.ezen.antpeople.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserDTO;
import com.ezen.antpeople.dto.sche.ScheUserListDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.repository.ScheRepository;
import com.ezen.antpeople.repository.USRepository;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.ScheService;

@Service("schedule")
public class ScheServiceImpl implements ScheService {
	private static final Logger logger = LoggerFactory.getLogger(ScheServiceImpl.class);
	
	private ScheRepository scheRepository;
	private UserRepository userRepository;
	private USRepository usRepository;
	
	public ScheServiceImpl(ScheRepository scheRepository, UserRepository userRepository,
			USRepository usRepository) {
		this.scheRepository = scheRepository;
		this.userRepository = userRepository;
		this.usRepository = usRepository;
	}
	
	//새로운 일정 저장
	@Override
	public void saveSchedules(Map<String, ScheDetailDTO> schedules) {
		for(String key : schedules.keySet()) {
			scheRepository.save(new ScheEntity(schedules.get(key)));
		}
	}
	
	//일정 가져오기 - 사장
	@Override
	public Set<ScheDetailDTO> findAllOnwer(int user_id) {
		Set<ScheDetailDTO>  schedules = new HashSet<ScheDetailDTO>();
		List<ScheEntity> entitys = new ArrayList<ScheEntity>(scheRepository.findByFromUser(userRepository.findById(user_id).get()));
		for(ScheEntity entity : entitys) {
			schedules.add(entity.buildDTO());
		}
		return schedules;
	}
	
	//일정 가져오기 - 월별
	@Override
	public ScheUserListDTO findAllMonth(int user_id, String startDate) {
		logger.info("월별 일정 리스트 출력 메소드 시작");
		Set<ScheDetailDTO> schedules = new HashSet<ScheDetailDTO>();
		List<ScheEntity> entitys = new ArrayList<ScheEntity>(scheRepository.findByFromUserAndStartDateStartingWith(userRepository.findById(user_id).get(),startDate));
		for(ScheEntity entity :entitys) {
			schedules.add(entity.buildDTO());
		}
		logger.info("월별 일정 리스트 :" + schedules.toString());
		ScheUserListDTO userAndMonth = new ScheUserListDTO(schedules);
		return userAndMonth;
	}

	//일정 수정, 삭제 하기
	@Override
	public void updateSchedule(Map<String, ScheDetailDTO> schedules) {
		for(String key : schedules.keySet()) {
			ScheEntity entity = new ScheEntity(schedules.get(key));
			if(!equalsScheduleId(entity)) { // 같은 일정이 없으면 일정 추가
				scheRepository.save(entity);
			}
			else if(!equalsScheduleManPower(entity)) { //같은 일정이지만 필요 직원수가 다르면 추가
				scheRepository.save(entity);
			}
		}
		
	}

	//월별 일정 존재 여부
	@Override
	public Set<String> isScheduleMonthList(UserDetailDTO user) {
		Set<String> monthList = new HashSet<String>();
		Set<ScheEntity> scheduleList = new HashSet<ScheEntity>(scheRepository.findByFromUser(new UserEntity(user)));
		for(ScheEntity schedule : scheduleList) {
			monthList.add(schedule.getStartDate().substring(0, 4));
		}
		
		return monthList;
	}
	
	//같은 일정인지 확인
	@Override
	public boolean equalsScheduleId(ScheEntity entity) {
		ScheEntity compare = scheRepository.findById(entity.getId()).get();
		if(entity.getSche_unique().equals(compare.getSche_unique()))
			return true;
		else 
			return false;
	}
	
	//같은 필요 직원 수 인지 확인
	@Override
	public boolean equalsScheduleManPower(ScheEntity entity) {
		ScheEntity compare = scheRepository.findById(entity.getId()).get();
		if(entity.getManPower() == compare.getManPower())
			return true;
		else 
			return false;
	}

	@Override
	public void deleteSchedule(Map<String, ScheDetailDTO> schedules) {
		//Set<ScheDetailDTO> scheduleList = new HashSet<ScheDetailDTO>(findAllMonth());
		
	}
	

}
