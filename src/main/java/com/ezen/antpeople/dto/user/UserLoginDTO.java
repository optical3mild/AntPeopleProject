package com.ezen.antpeople.dto.user;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class UserLoginDTO implements Serializable {
	public String email;
	public String password;
	
	public UserLoginDTO() {}
	
	public UserLoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	

}
