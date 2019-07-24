package com.ezen.antpeople.dto.user;

import lombok.Getter;

@Getter
public class RoleDTO {
	private int role_id;
	private String role;
	
	
	public RoleDTO() {}
	
	//회원 가입시 이용하는 생성자
	public RoleDTO(int role_id) {
		this.role_id=role_id;
	}
	
	public RoleDTO(int role_id, String role) {
		this.role_id = role_id;
		this.role = role;
	}

}
