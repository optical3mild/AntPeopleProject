package com.ezen.antpeople.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

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
			,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		UserEntity userEntity = new UserEntity();
		userEntity = userRepository.findByEmail(email);
		return userEntity.buildDomain();
	}

	@Override
	public void saveUser(UserDTO user) {
		UserEntity userEntity = new UserEntity();
		RoleEntity roleEntity = roleRepository.findByRole("ADMIN");
		userEntity.buildEntity(user, new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
		userEntity.setPassword(user.getPassword());
		userRepository.save(userEntity);
	}

	@Override
	public UserDTO getUser(int id) {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (userEntity.isPresent())
			return userEntity.get().buildDomain();
		else return new UserDTO();
	}

	@Override
	public void deleteUser(UserDTO user) {
		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		userRepository.deleteById(userEntity.getId());
		
	}

	//정보 업데이트
	//view로부터 유저의 변경 정보를 얻는다. (이때, 이메일은 제외한다.)
	//예를 들어 유저의 정보 중 주소만 변경 되었다고 할 경우, 나머지 사항은 원래 값이 들어 가있다.
	//이때, Entity의 메소드를 통해 주소,전화번호만 변경 가능하게 한다. (초기테스트)
	
	@Override
	public void updateUser(UserDTO user) {
		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		userEntity.updateUser(user);
	}
	
	

}
