package com.ezen.antpeople.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	
	public UserDTO() {}
	
	public UserDTO(int user_id, String email, String password, String name, String active, String address, String phone) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.active = active;
		this.address = address;
		this.phone = phone;
	}
	
	public UserDTO( String email, String password, String name, String active, String address, String phone) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.active = active;
		this.address = address;
		this.phone = phone;
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
		return "UserDTO [id=" + user_id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", email=" + email
				+ ", password=" + password + ", name=" + name + ", active=" + active + ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
	public void loginUser(String email, String password) {
		this.email = email;
		this.password = password;
	}
	

}
