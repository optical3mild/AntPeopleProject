package com.ezen.antpeople.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezen.antpeople.DTO.UserDTO;

@Entity
@Table(name="user")
public class UserEntity {
	
	@Id
	@Column(name="user_id")
	private String id;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	private String address;
	private String phone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UserDTO buildDomain() {
		UserDTO user = new UserDTO();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setAddress(address);
		user.setPhone(phone);
		return user;
	}

	
}
