package com.ezen.antpeople.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.ezen.antpeople.entity.RoleEntity;

import lombok.Getter;

@Getter
public class UserDetailDTO implements Serializable {
	
    protected int user_id;
	private String email;
	private String password;
	private String name;
	private Integer state;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	private RoleDTO role;
	private StoreDTO store;
	
	public UserDetailDTO() {}

	//사용자 상세정보 사용 시 
	public UserDetailDTO(int user_id, String email, String password, String name, Integer state, LocalDateTime createdAt,
			LocalDateTime updatedAt, RoleDTO role, StoreDTO store) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.store = store;
	}
	
	// 사용자 회원 가입 시
	public UserDetailDTO(String email, String password, String name, Integer state,
			 RoleDTO role, StoreDTO store) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.store = store;
	}

	@Override
	public String toString() {
		return "UserDetailDTO [user_id=" + user_id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", state=" + state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role=" + role.getRole()
				+ ", store=" + store.getStore() + "]";
	}
	
	

}
