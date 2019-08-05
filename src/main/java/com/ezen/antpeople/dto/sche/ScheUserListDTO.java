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
	private Set<ScheUserDTO> userToScheList;
	private String month;
	
	public ScheUserListDTO(Set<ScheDetailDTO> scheduleList,Set<ScheUserDTO> userToScheList, String month) {
		this.scheduleList = scheduleList;
		this.userToScheList = userToScheList;
		this.month = month;
		
	}
	
	@Override
	public String toString() {
		//스케줄 생성 함수
		String schedules = scheduleString(this.scheduleList);
		String users = userString(this.userToScheList);
		log.info(users);
		if(users != "")
			return "{\""+this.month+"\":"+schedules+","+users+"}";
		else
			return "{\""+this.month+"\":"+schedules+"}";
	}
	
	//스케줄 생성 함수
	public String scheduleString(Set<ScheDetailDTO> scheduleList) {
		String schedules = "{";
		for(ScheDetailDTO schedule : scheduleList)
			schedules += "\""+ schedule.getId() + "\":"+ schedule.toString()+",";
		schedules = schedules.substring(0, schedules.length()-1);
		schedules += "}";
		return schedules;
	}
	
	//사용자별 일정 리스트 생성 함수
	public String userString(Set<ScheUserDTO> userToScheList) {
		Optional<Map<String, Set<String>>> userSchedule = Optional.of(userScheduleList(userToScheList));
		String users = "";
		if(!userSchedule.get().isEmpty()) {
			log.info("일정에 대한 사용자 존재");
			for(String user_id : userSchedule.get().keySet()) {
				users += "\""+user_id+"\":[";
				for(String schedule : userSchedule.get().get(user_id))
					users +="\""+schedule +"\",";
				users = users.substring(0, users.length()-1) +"] ,";
			}
			users = users.substring(0, users.length()-1);
		}
		return users;
	}
	
	//사용자별 신청 일정 리스트 만들기
	public Map<String, Set<String>> userScheduleList(Set<ScheUserDTO> scheduleList){
		log.info("일정 리스트 : " + scheduleList);
		Map<String,Set<String>> userSchedule = new HashMap<String,Set<String>>();
		
		for(ScheUserDTO schedule : scheduleList) {
			Optional<UserDetailDTO> user = Optional.ofNullable(schedule.getUser());
			String scheduleId = schedule.getUnique();
			if(user.isPresent()) {
				Set<String> schedules = new HashSet<String>();
				String key = user.get().getUser_id()+"_"+user.get().getName();
				if(userSchedule.get(key) != null) {
					log.info("기존의 등록된 정보가 있는 회원");
					schedules = userSchedule.get(key);
				}
				schedules.add(scheduleId);
				log.info("해당 유저가 신청한 schedule : " + schedule);
				userSchedule.put(key,schedules);
			}			
		}
		return userSchedule;
	}
}
