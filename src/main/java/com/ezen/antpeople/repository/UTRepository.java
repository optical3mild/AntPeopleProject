package com.ezen.antpeople.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.TodoRelation;

@Repository
public interface UTRepository extends JpaRepository<TodoRelation, Integer> {
	List<TodoRelation> findByToUser_id(int user_id);
	List<TodoRelation> findByToUser_idAndState(int user_id, boolean state);
	TodoRelation findByTodo_idAndToUser_id(int id, int user_id);
	
	@Transactional
	void deleteByTodo_id(int id);
	
}
