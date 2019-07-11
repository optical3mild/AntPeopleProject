package com.ezen.antpeople.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDTO implements Serializable {
	
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
	private String email;
	private String password;
	private String name;
	private String active;
	private String address;
	private String phone;
	
	public UserDTO() {}
	
	public UserDTO(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String password,
			String name, String active, String address, String phone) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.email = email;
		this.password = password;
		this.name = name;
		this.active = active;
		this.address = address;
		this.phone = phone;
	}
	public Long getId() {
		return id;
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
		return "UserDTO [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", email=" + email
				+ ", password=" + password + ", name=" + name + ", active=" + active + ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
	
	

}
