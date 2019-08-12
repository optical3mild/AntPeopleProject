package com.ezen.antpeople.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{
	
	@Transactional
	List<TodoEntity> findByFromUser_id(int user_id);
}
