package com.ezen.antpeople.service;

import java.util.List;

import com.ezen.antpeople.DTO.UserDTO;

public interface UserService {
	public UserDTO getUser(String id);
	
	public List<UserDTO> getUsers();
	
	public List<UserDTO> getCustomerByPage(int index, int size);
	
	public void saveCustomer(UserDTO userDTO);
	
	public void updateUser(UserDTO userDTO);
	
	public void deleteCustomer(String id);
	
	
	
	

}
