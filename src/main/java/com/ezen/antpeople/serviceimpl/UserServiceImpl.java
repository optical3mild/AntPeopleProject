package com.ezen.antpeople.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.entity.RoleEntity;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.repository.RoleRepository;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.UserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//생성자 의존성 추가
	private UserServiceImpl(UserRepository userRepository 
			,RoleRepository roleRepository
			,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserEntity findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(UserDTO user) {
		UserEntity userEntity = new UserEntity();
		RoleEntity roleEntity = roleRepository.findByRole("Admin");
		userEntity.buildEntity(user, new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(userEntity);
	}

}
