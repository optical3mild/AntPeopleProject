package com.ezen.antpeople.service;

import java.util.Map;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;

public interface ScheService {
	//일정 저장하기
	public void saveSchedules(Map<String, ScheDetailDTO> schedules);
	
}