package com.ezen.antpeople.service;

import java.util.Map;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;

public interface ScheService {
	
	//일정 저장하기
	public void saveSchedules(Map<String, ScheDetailDTO> schedules);
	
	//사장의 일정 가져오기
	public Map<String, ScheDetailDTO> findAllOnwer(int user_id);
	public Map<String, ScheDetailDTO> findAllMonth(int user_id, String startDate);
	
	//일정 변경하기 
	public void updateSchedule();
	
	//일정 유무
	public boolean isMonthSchedule();
	
}