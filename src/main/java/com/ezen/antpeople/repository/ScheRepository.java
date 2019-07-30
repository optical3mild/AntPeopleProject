package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.entity.UserEntity;

@Repository("schedule")
public interface ScheRepository extends JpaRepository<ScheEntity, Integer> {
	List<ScheEntity> findByFromUser(UserEntity user);
	List<ScheEntity> findByFromUserAndStartDate(UserEntity user, String startDate);

}
