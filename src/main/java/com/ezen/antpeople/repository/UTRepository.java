package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.TodoRelation;
import com.ezen.antpeople.entity.UserEntity;

@Repository
public interface UTRepository extends JpaRepository<TodoRelation, Integer> {
	List<TodoRelation> findByToUser(UserEntity user);
	TodoRelation findByTodo_id(Integer id);
}
