package com.ezen.antpeople.service;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.entity.UserEntity;

public interface UserService {
	public UserDTO getUser(int id);
	public UserDTO findUserByEmail(String email);
	public void saveUser(UserDTO user);
	public void deleteUser(UserDTO user);
	public void updateUser(UserDTO user);
}
