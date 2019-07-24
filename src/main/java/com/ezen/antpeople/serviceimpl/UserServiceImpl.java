package com.ezen.antpeople.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.antpeople.controller.user.UserController;
import com.ezen.antpeople.dto.user.RoleDTO;
import com.ezen.antpeople.dto.user.StoreDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.dto.user.UserLoginDTO;
import com.ezen.antpeople.entity.RoleEntity;
import com.ezen.antpeople.entity.StoreEntity;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.repository.RoleRepository;
import com.ezen.antpeople.repository.StoreRepository;
import com.ezen.antpeople.repository.UserRepository;
import com.ezen.antpeople.service.UserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private StoreRepository storeRepositoty;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//생성자 의존성 추가
	private UserServiceImpl(UserRepository userRepository 
			,RoleRepository roleRepository
			,BCryptPasswordEncoder bCryptPasswordEncoder
			,StoreRepository storeRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.storeRepositoty = storeRepository;
	}
	
	//회원 정보 확인 
	@Override
	public UserDetailDTO findByEmail(String email) {
		Optional<UserEntity> userDetail = userRepository.findByEmail(email);
		return userDetail.get().buildDTO();
	}

	// 로그인 로직
	@Override
	public UserDetailDTO verifiedPassword(UserLoginDTO userLogin) {
		UserDetailDTO user = loadUserByUsername(userLogin.getEmail());
		if(bCryptPasswordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
			return user;
		}
		else 
			return null;
	}

	//회원 가입 로직 
	@Override
	public String userSignUp(UserDetailDTO udd) {
		UserEntity entity = new UserEntity(udd,bCryptPasswordEncoder.encode(udd.getPassword()));
		Optional<UserEntity> checkEmail = userRepository.findByEmail(udd.getEmail());
		if(!checkEmail.isPresent()) {
			userRepository.save(entity);
			return udd.getEmail();
		}else
			return  "회원 가입에 실패하였습니다.";
	}
	
	//회원 탈퇴 로직
	@Override
	public String userDelete(String email, String password) {
		String msg = new String();
		Optional<UserEntity> entity = userRepository.findByEmail(email);
		if(entity.get().getPassword().equals(password)) {
			userRepository.delete(entity.get());
			msg = "정상적으로 회원 탈퇴가 되었습니다.";
		} else {
			msg = "비밀번호가 틀렸습니다. 다시 입력해 주세요.";
		}
		return msg;
			
		
	}
	//회원 가입시 역할, 지점 리스트 출력
	@Override
	public List<RoleDTO> RoleList() {
		List<RoleEntity> entitys = new ArrayList(roleRepository.findAll());
		List<RoleDTO> roles = new ArrayList();
		for(RoleEntity entity : entitys)
			roles.add(entity.buildDTO());
		return roles;
	}

	@Override
	public List<StoreDTO> StoreList() {
		List<StoreEntity> entitys = new ArrayList(storeRepositoty.findAll());
		List<StoreDTO> stores = new ArrayList();
		for(StoreEntity entity : entitys)
			stores.add(entity.buildDTO());
		return stores;
	}

	@Override
	public List<UserDetailDTO> findByAll() {
		List<UserEntity> entitys = new ArrayList(userRepository.findAll());
		List<UserDetailDTO> userList = new ArrayList();
		for(UserEntity entity : entitys)
			userList.add(entity.buildDTO());
		return userList;
	}
	
	@Override
	public UserDetailDTO loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("로드 유저 네임");
		Optional<UserEntity> entity = userRepository.findByEmail(email);
		if(!entity.isPresent())
			return null;
		UserDetailDTO user = roleUser(entity.get());
        return user;
	}
	
	//인증 권한 부여
	@Override
	public UserDetailDTO roleUser(UserEntity entity) {
		UserDetailDTO loginUser = entity.buildDTO();
		switch(loginUser.getRole().getRole()) {
		case "관리자":
			loginUser.setAuthentic("ROLE_ADMIN");
			break;
		case "사장":
			loginUser.setAuthentic("ROLE_MANAGER");
			break;
		case "직원":
			loginUser.setAuthentic("ROLE_MEMBER");
			break;
		}
        return loginUser;
    }
	

	

}
