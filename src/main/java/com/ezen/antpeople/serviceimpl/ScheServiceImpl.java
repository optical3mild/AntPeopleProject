package com.ezen.antpeople.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserDTO;
import com.ezen.antpeople.dto.sche.ScheUserListDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.entity.ScheRelation;
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
	
	//일정 가져오기 - 직원
	@Override
	public Set<ScheDetailDTO> findAllStaff(UserDetailDTO user, String date) {
		Set<ScheDetailDTO>  schedules = new HashSet<ScheDetailDTO>();
		List<ScheEntity> entitys = 
				new ArrayList<ScheEntity>
		(scheRepository.findByFromUserStoreStoreAndStartDateStartingWith(user.getStore().getStore(),date));
		for(ScheEntity entity : entitys) {
			schedules.add(entity.buildDTO());
		}
		return schedules;
	}
	
	//일정 가져오기 - 월별일정 + 신청 인원
	@Override
	public ScheUserListDTO findAllMonthAndUser(UserDetailDTO user, String startDate) {
		logger.info("월별 일정 리스트 출력 메소드 시작");
		Set<ScheDetailDTO> schedules = new HashSet<ScheDetailDTO>();
		Set<ScheUserDTO> userToSchedules = new HashSet<ScheUserDTO>();
		List<ScheEntity> entitys = new ArrayList<ScheEntity>(scheRepository.findByFromUserAndStartDateStartingWith(userRepository.findById(user.getUser_id()).get(),startDate));
		Optional<List<ScheRelation>> UserEntitys = 
				Optional.of(usRepository.findByToUserStoreStoreAndScheFromUserIdAndScheStartDateStartingWith(user.getStore().getStore(),user.getUser_id(),startDate));
		for(ScheEntity entity :entitys) {
			schedules.add(entity.buildDTO());
		}
		if(UserEntitys.isPresent()) {
			for(ScheRelation entity: UserEntitys.get()) {
				userToSchedules.add(entity.buildDTO());
			}
		}
		logger.info("월별 일정 리스트 :" + schedules.toString());
		ScheUserListDTO userAndMonth = new ScheUserListDTO(schedules,userToSchedules,startDate);
		return userAndMonth;
	}
	
	//일정 가져오기 - 월별일정
	@Override
	public Set<ScheDetailDTO> findAllMonth(int user_id, String startDate) {
		logger.info("월별 일정 리스트 출력 메소드 시작");
		Set<ScheDetailDTO> schedules = new HashSet<ScheDetailDTO>();
		List<ScheEntity> entitys = new ArrayList<ScheEntity>(scheRepository.findByFromUserAndStartDateStartingWith(userRepository.findById(user_id).get(),startDate));
		for(ScheEntity entity :entitys) {
			schedules.add(entity.buildDTO());
		}
		return schedules;
	}

	//일정 수정, 삭제 하기
	@Override
	public void updateSchedule(Map<String, ScheDetailDTO> schedules) {
		for(String key : schedules.keySet()) {
			ScheEntity entity = new ScheEntity(schedules.get(key));
			logger.info("현재 일정 : " + entity.getUnique());
			if(entity.getState() == -1) {
				logger.info("삭제할 일정 : " + entity.getUnique());
				usRepository.deleteBySche_unique(entity.getUnique());
				scheRepository.deleteByUnique(entity.getUnique());
				continue;
			}
			logger.info("새로운 일정 검사");
			if(!equalsScheduleId(entity)) { // 같은 일정이 없으면 일정 추가
				logger.info("새로운 일정");
				scheRepository.save(entity);
				continue;
			} 
			if(!equalsScheduleManPower(entity)) { //같은 일정이지만 필요 직원수가 다르면 추가
				logger.info("일정 변경");
				Optional<ScheEntity> newEntity = scheRepository.findByUnique(entity.getUnique());
				newEntity.get().updateManPower(entity.getManPower());
				scheRepository.save(newEntity.get());
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
	
	//일정 존재 확인
	@Override
	public boolean equalsScheduleId(ScheEntity entity) {
		Optional<ScheEntity> compare = scheRepository.findByUnique(entity.getUnique());
		logger.info("일정 존재 여부 :" + compare);
		if(compare.isPresent())
			return true;
		else 
			return false;
	}
	
	//같은 필요 직원 수 인지 확인
	@Override
	public boolean equalsScheduleManPower(ScheEntity entity) {
		Optional<ScheEntity> compare = scheRepository.findByUnique(entity.getUnique());
		if(entity.getManPower() == compare.get().getManPower())
			return true;
		else 
			return false;
	}
	
	//일정 삭제 하기
	@Override
	public void deleteSchedule(Map<String, ScheDetailDTO> schedules) {
		
	}
	
	//일정에 근무 신청 시
	@Override
	public void updateUserSchedule(UserDetailDTO user, String schedule_id) {
		//1. 해당 일정의 정보 가져오기
		Optional<ScheEntity> entity = scheRepository.findByUnique(schedule_id);
		List<UserEntity> toUsers = new ArrayList<UserEntity>();	
		Optional<List<UserEntity>> isToUsers = Optional.of(entity.get().getToUsers());
		logger.info("toUsers에 들어있는 항목 : " + isToUsers.get());
		if(isToUsers.isPresent()) {
			toUsers = isToUsers.get();
			toUsers.add(new UserEntity(user));
		}
		entity.get().updatePeopleCountAndUser(toUsers);
		scheRepository.save(entity.get());
		logger.info("근무 신청 완료");
	}

	//일정 승인,거절하는 메소드
	@Override
	public void isPermissionSchedule(UserDetailDTO user, String schedule_id, int state) {
		Optional<ScheEntity> entity = scheRepository.findByUnique(schedule_id);
		ScheUserDTO userToSche = new ScheUserDTO(user, entity.get().getId(),entity.get().getUnique(),state);
		usRepository.save(new ScheRelation(userToSche));
		if(state == 3) {
			entity.get().downPeopleCount();
			scheRepository.save(entity.get());
			logger.info("일정 신청 거절");
		}
		logger.info("일정 승인 or 거절 완료");
	}

}
