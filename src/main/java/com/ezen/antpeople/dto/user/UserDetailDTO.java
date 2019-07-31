package com.ezen.antpeople.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class UserDetailDTO implements Serializable {
	
	private static final long serialVersionUID = 1278219708789765219L;

	protected int user_id;
    
    @Email(message ="이메일 형식으로 적어주세요")
	@NotEmpty(message = "이메일은 필수 입니다.")
	private String email;
    
    @Length(min=5, message="비밀번호를 5자 이상 입력해 주세요")
	@NotEmpty(message="비밀번호를 입력해 주세요")
	private String password;
    
    @NotEmpty(message="이름은 필수 입니다.")
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
	public UserDetailDTO(String email, String password, String name,
			 RoleDTO role, StoreDTO store) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.store = store;
	}
	
	public UserDetailDTO(int user_id, String email, String name,
			 RoleDTO role, StoreDTO store) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.name = name;
		this.role = role;
		this.store = store;
	}
	
	//Id, Name 받아오는 메소드
	public UserDetailDTO(int user_id, String name) {
		this.user_id = user_id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDetailDTO [user_id=" + user_id + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", state=" + state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role=" + role.getRole_id()
				+ ", store=" + store.getStore_id() + "]";
	}

}
