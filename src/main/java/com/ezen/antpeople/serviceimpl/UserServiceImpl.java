package com.ezen.antpeople.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.antpeople.DTO.UserDTO;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.UserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDTO getUserById(String id) {
		UserEntity entity = repository.findById(id);
		return entity.buildDomain();
	}

	@Override
	public List<UserDTO> getUsers() {
		
		return null;
	}

	@Override
	public List<UserDTO> getCustomerByPage(int index, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCustomer(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(String id) {
		// TODO Auto-generated method stub
		
	}
	

}
