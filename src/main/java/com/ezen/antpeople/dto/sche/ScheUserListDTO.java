package com.ezen.antpeople.dto.sche;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ScheUserListDTO {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private Set<ScheDetailDTO> scheduleList;
	
	public ScheUserListDTO(Set<ScheDetailDTO> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	@Override
	public String toString() {
		//스케줄 생성 함수
		String schedules = scheduleString(this.scheduleList);
		String users = userString(this.scheduleList);
		
		
		return "{"+schedules+","+users+"}";
	}
	
	//스케줄 생성 함수
	public String scheduleString(Set<ScheDetailDTO> scheduleList) {
		String schedules = "{";
		for(ScheDetailDTO schedule : scheduleList)
			schedules += "\""+ schedule.getId() + "\":"+ schedule.toString()+",";
		schedules = schedules.substring(0, schedules.length()-1);
		schedules += "}";
		log.info("생성된 일정 함수 String : " + schedules);
		return schedules;
	}
	
	//사용자별 일정 리스트 생성 함수
	public String userString(Set<ScheDetailDTO> scheduleList) {
		Map<Integer, Set<String>> userSchedule = 
				new HashMap<Integer, Set<String>>(userScheduleList(scheduleList));
		String users = new String();
		for(Integer user_id : userSchedule.keySet()) {
			log.info("해당 사용자의 일정 리스트"+userSchedule.get(user_id).toString());
			users += "\""+user_id+"\":"+userSchedule.get(user_id).toString()+",";
		}
		users = users.substring(0, users.length()-1);
		log.info("생성된 사용자 함수 String : " + users);
		return users;
	}
	
	//사용자별 신청 일정 리스트 만들기
	public Map<Integer, Set<String>> userScheduleList(Set<ScheDetailDTO> scheduleList){
		Map<Integer,Set<String>> userSchedule = new HashMap<Integer,Set<String>>();
		
		for(ScheDetailDTO schedule : scheduleList) {
			log.info("일정 별 사용자 리스트 :" + schedule.getToUsers().toString());
			Optional<List<UserDetailDTO>> users = Optional.empty();
			String scheduleId = schedule.getId();
			log.info("현재 일정 ID : " + scheduleId);
			users = Optional.of(schedule.getToUsers());
			if(users.isPresent()) {
				for(UserDetailDTO user : users.get()) {
					Set<String> schedules = new HashSet<String>();
					int user_id = user.getUser_id();
					if(userSchedule.get(user_id) != null) {
						schedules = userSchedule.get(user_id);
						log.info("사용자 키 존재");
					}
					log.info("schedule에 들어있는 값 :" +schedules);
					schedules.add(scheduleId);
					log.info("schedule에 추가된 값 :" +schedules);
					userSchedule.put(user_id,schedules);
				}
			}
			log.info("현재 일정에 신청한 사람 : " + userSchedule);
			
		}
		return userSchedule;
	}
	
}
