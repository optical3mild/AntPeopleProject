package com.ezen.antpeople.serviceimpl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.repository.ScheRepository;
import com.ezen.antpeople.service.ScheService;

@Service("Schedule")
public class ScheServiceImpl implements ScheService {
	private static final Logger logger = LoggerFactory.getLogger(ScheServiceImpl.class);
	
	private ScheRepository scheRepository;
	
	public ScheServiceImpl(ScheRepository scheRepository) {
		this.scheRepository = scheRepository;
	}
	
	//일정 저장하기
	@Override
	public void saveSchedules(Map<String, ScheDetailDTO> schedules) {
		for(String key : schedules.keySet()) {
			scheRepository.save(new ScheEntity(schedules.get(key)));
		}
	}
	
	

}
