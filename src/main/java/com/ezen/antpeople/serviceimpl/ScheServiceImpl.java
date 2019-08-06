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
	
	//일정 가져오기 - 직원신청가능 목록
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
	
	//일정 가져오기 - 직원신청 완료 목록
		@Override
		public Set<ScheDetailDTO> staffApply(UserDetailDTO user, String date) {
			Set<ScheDetailDTO>  schedules = new HashSet<ScheDetailDTO>();
			Set<ScheRelation> entitys = 
					new HashSet<ScheRelation>
			(usRepository.findByToUser_idAndScheStartDateStartingWith(user.getUser_id(),date));
			for(ScheRelation entity : entitys) {
				schedules.add(entity.buildDetailDTO());
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
				Optional.of(usRepository.findByToUserStoreStoreAndScheFromUserIdAndStateAndScheStartDateStartingWith(user.getStore().getStore(),user.getUser_id(),1,startDate));
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
	
	//일정 가져오기 - 월별일정 + 해당 사용자
	@Override
	public ScheUserListDTO findAllMonthAndStaff(UserDetailDTO user, String startDate) {
		logger.info("월별 일정 리스트 출력 메소드 시작");
		Set<ScheDetailDTO> schedules = new HashSet<ScheDetailDTO>();
		Set<ScheUserDTO> userToSchedules = new HashSet<ScheUserDTO>();
		List<ScheEntity> entitys = new ArrayList<ScheEntity>(scheRepository.findByFromUserStoreStoreAndStartDateStartingWith(user.getStore().getStore(),startDate));
		Optional<List<ScheRelation>> UserEntitys = 
				Optional.of(usRepository.findByToUser_idAndScheStartDateStartingWith(user.getUser_id(),startDate));
		for(ScheEntity entity :entitys) {
			schedules.add(entity.buildDTO());
		}
		if(UserEntitys.isPresent()) {
			for(ScheRelation entity: UserEntitys.get()) {
				userToSchedules.add(entity.buildDTO());
			}
		}
		logger.info("월별 일정 리스트 :" + schedules.toString());
		logger.info("일정 신청한 직원 리스트 : " + userToSchedules.toString());
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

	//사장이 등록한 일정 수정, 삭제 하기
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
	public String updateUserSchedule(UserDetailDTO user, String schedule_id) {
		logger.info("일정 유니크 아이디 : " + schedule_id);
		ScheEntity entity = scheRepository.findByUnique(schedule_id).get();
		logger.info(entity.toString());
		ScheRelation userSchedule = new ScheRelation(new UserEntity(user), entity);
		usRepository.save(userSchedule);
		entity.updatePeopleCount(true); //인원수 증가
		scheRepository.save(entity);
		logger.info("근무 신청 완료");
		
		return "{\""+entity.getUnique()+"\":" +entity.buildDTO().toString()+"}";
	}
	
	//신청된 일정 취소하기
	@Override
	public String deleteSchedule(UserDetailDTO user, String schedule_id) {
		logger.info("일정 유니크 아이디 : " + schedule_id);
		ScheEntity entity = scheRepository.findByUnique(schedule_id).get();
		logger.info(entity.toString());
		ScheRelation userSchedule = new ScheRelation(new UserEntity(user), entity);
		usRepository.delete(userSchedule);
		entity.updatePeopleCount(false); //인원수 차감
		scheRepository.save(entity);
		logger.info("근무 신청 취소 완료");
		return "{\""+entity.getUnique()+"\":" +entity.buildDTO().toString()+"}";
		
	}

	//일정 승인,거절하는 메소드
	@Override
	public void permissionSchedule(Map<Integer,Set<String>> schedules, String month) {
		List<ScheRelation> entitys = new ArrayList<ScheRelation>();
		Set<String> deletedSchedules = new HashSet<String>();
		for(Integer key : schedules.keySet()) {
			logger.info("key값 : " + key);
			entitys = usRepository.findByToUser_idAndScheStartDateStartingWith(key, month);
			deletedSchedules = schedules.get(key);
		}
		logger.info("사용자의 일정 신청 리스트 : " + entitys.toString());
		logger.info("일정 거절 리스트 : " + deletedSchedules.toString());
		//만약 entity리스트의 요소가 deletedSchedules의 요소중 하나랑 같으면 일정 승인 거절 표시
		for(ScheRelation entity : entitys) {
			ScheUserDTO schedule = entity.buildDTO();
			Optional<ScheEntity> scheEntity = scheRepository.findByUnique(schedule.getUnique());
			for(String deletedSchedule : deletedSchedules) {
				String schedule_unigue = schedule.getUnique();
				logger.info("승인 신청 일정 : " + schedule_unigue);
				logger.info("승인 신청 거절 일정 :"+deletedSchedule);
				if(schedule_unigue.equals(deletedSchedule)) {
					schedule.updateScheState(3); //일정 승인 거절
					scheEntity.get().updatePeopleCount(false); //인원수 차감
					scheRepository.save(scheEntity.get());
					logger.info("일정 신청 거절");
					break;
				} else {
					logger.info("거절이 없는 일정");
					schedule.updateScheState(2);//일정 승인 완료
				}
			}
			usRepository.save(new ScheRelation(schedule));
		}
		logger.info("일정 승인 or 거절 완료");
	}


	//당일 근무자 리스트 가져오기
	@Override
	public List<ScheUserDTO> todayStaffList(String store, String month) {
		List<ScheUserDTO> userList = new ArrayList<ScheUserDTO>();
		List<ScheRelation> entitys = new ArrayList<ScheRelation>(usRepository.findByToUserStoreStoreAndScheStartDate(store, month));
		for(ScheRelation entity : entitys) {
			userList.add(entity.buildDTO());
		}
		return userList;
	}



}
