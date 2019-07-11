package com.ezen.antpeople.service;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.entity.UserEntity;

public interface UserService {
	public UserEntity findUserByEmail(String email);
	public void saveUser(UserDTO user);
}
