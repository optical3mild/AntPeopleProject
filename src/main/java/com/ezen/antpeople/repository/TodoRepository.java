package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.TodoEntity;
import com.ezen.antpeople.entity.UserEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{
	List<TodoEntity> findByFromUser_id(int user_id);
}
