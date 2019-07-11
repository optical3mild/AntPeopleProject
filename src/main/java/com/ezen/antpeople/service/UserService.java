package com.ezen.antpeople.service;

import com.ezen.antpeople.dto.UserDTO;

public interface UserService {
	public UserDTO findUserByEmail(String email);
	public void saveUser(UserDTO user);
}
