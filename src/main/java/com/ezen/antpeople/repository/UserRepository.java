package com.ezen.antpeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
}
