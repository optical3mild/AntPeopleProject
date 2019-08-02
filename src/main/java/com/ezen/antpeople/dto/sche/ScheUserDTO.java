package com.ezen.antpeople.dto.sche;

import java.util.HashSet;
import java.util.Set;

import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ScheUserDTO {
	private UserDetailDTO user;
	private int schedule_id;
	private String unique;
	private Integer state;
	
	public ScheUserDTO(UserDetailDTO user, int schedule_id,String unique, Integer state) {
		this.user = user;
		this.schedule_id = schedule_id;
		this.unique = unique;
		this.state = state;
	}

	@Override
	public String toString() {
		return "schedule_id=" + user.getName();
	}
	
	

}
