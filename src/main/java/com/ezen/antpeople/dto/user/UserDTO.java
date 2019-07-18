package com.ezen.antpeople.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.ezen.antpeople.entity.RoleEntity;

public class UserDTO implements Serializable {
	
    protected int user_id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
	private String email;
	private String password;
	private String name;
	private String active;
	private String address;
	private String phone;
	private int role_id;
	private Set<RoleEntity> role;
	
	public UserDTO() {}
	
	//데이터 출력
	public UserDTO(int user_id, String email, String password, String name, String active, String address, String phone, Set<RoleEntity> role) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.active = active;
		this.address = address;
		this.phone = phone;
		this.role = role;
	}
	
	//데이터 입력 
	public UserDTO( String email, String password, String name, String active, String address, String phone, int role_id) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.active = active;
		this.address = address;
		this.phone = phone;
		this.role_id = role_id;
	}
	public Integer getId() {
		return user_id;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getActive() {
		return active;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	
	
	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", email="
				+ email + ", password=" + password + ", name=" + name + ", active=" + active + ", address=" + address
				+ ", phone=" + phone + "]";
	}

	public void loginUser(String email, String password) {
		this.email = email;
		this.password = password;
	}
	

}
