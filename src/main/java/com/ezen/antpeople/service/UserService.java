package com.ezen.antpeople.service;

import java.util.List;

import com.ezen.antpeople.dto.UserDTO;

public interface UserService {
	UserDTO getUserById(String id);
	List<UserDTO> getUsers();
	List<UserDTO> getCustomerByPage(int index, int size);
	void saveCustomer(UserDTO userDTO);
	void updateUser(UserDTO userDTO);
	void deleteCustomer(String id);
}
