package com.ezen.antpeople.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.UserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	
	//생성자 의존성 추가
	private UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public void saveUser(UserDTO user) {
		UserEntity entity = new UserEntity();
		
		entity.buildEntity(user);
		repository.save(entity);
	}

}
