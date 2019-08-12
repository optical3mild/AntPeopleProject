package com.ezen.antpeople.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserTodoDTO {
	private int userId;
	private String email;
	private String userName;
	private int role;
	
	public UserTodoDTO(int id, String email, String userName, int role) {
		this.userId = id;
		this.email = email;
		this.userName = userName;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "{\"user_id\":\"" + this.userId + "\","
				+ "\"userName\":\"" + this.userName + "\","
				+ "\"role\":\""+this.role+"\"}";
	}
	

}
