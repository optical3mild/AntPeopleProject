package com.ezen.antpeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.antpeople.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findById(String id);


}
