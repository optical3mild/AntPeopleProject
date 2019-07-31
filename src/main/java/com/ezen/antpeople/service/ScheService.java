package com.ezen.antpeople.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.entity.ScheEntity;

public interface ScheService {
	
	//일정 저장하기
	public void saveSchedules(Map<String, ScheDetailDTO> schedules);
	
	//사장의 일정 가져오기
	public Set<ScheDetailDTO> findAllOnwer(int user_id);
	public Set<ScheDetailDTO> findAllMonth(int user_id, String startDate);
	
	//일정 변경하기, 삭제하기
	public void updateSchedule(Map<String, ScheDetailDTO> schedules);
	public void deleteSchedule(Map<String, ScheDetailDTO> schedules);
	
	//일정 유무
	public Set<String> isScheduleMonthList(UserDetailDTO user);
	
	//일정 확인 하기 (비교함수)
	//ScheEntity의 UserDetail정보가 달라서 id와 manpower를 따로 비교해야함
	public boolean equalsScheduleId(ScheEntity entity);
	public boolean equalsScheduleManPower(ScheEntity entity);
	
}