package com.ezen.antpeople.model;

import com.ezen.antpeople.DTO.UserDTO;

public class UserModel {
	private String id;
	private String password;
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
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", phone="
				+ phone + "]";
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
	
	public void buildModel(UserDTO user) {
		id = user.getId();
		password = user.getPassword();
		name = user.getName();
		address = user.getAddress();
		phone = user.getPhone();
	}

}
