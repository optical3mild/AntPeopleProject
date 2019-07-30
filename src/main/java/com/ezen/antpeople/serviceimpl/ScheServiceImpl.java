package com.ezen.antpeople.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.repository.ScheRepository;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.ScheService;

@Service("Schedule")
public class ScheServiceImpl implements ScheService {
	private static final Logger logger = LoggerFactory.getLogger(ScheServiceImpl.class);
	
	private ScheRepository scheRepository;
	private UserRepository userRepository;
	
	public ScheServiceImpl(ScheRepository scheRepository, UserRepository userRepository) {
		this.scheRepository = scheRepository;
		this.userRepository = userRepository;
	}
	
	//일정 저장하기
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
		public JSONObject findAllMonth(int user_id, String startDate) {
			startDate = startDate + "*";
			List<ScheEntity> entitys = new ArrayList<ScheEntity>(scheRepository.findByFromUserAndStartDate(userRepository.findById(user_id).get(),startDate));
			JSONObject jsonObject = new JSONObject();
			
			for(ScheEntity entity :entitys) {
				jsonObject.put(entity.getSche_unique(), entity.buildDTO());
			}
			return jsonObject;
		}

	@Override
	public void updateSchedule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMonthSchedule() {
		return false;
	}
	
	

}
