package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.ScheEntity;
import com.ezen.antpeople.entity.UserEntity;

@Repository()
public interface ScheRepository extends JpaRepository<ScheEntity, Integer> {
	ScheEntity findByUnique(String sche_unique);
	List<ScheEntity> findByFromUser(UserEntity user);
	List<ScheEntity> findByFromUserAndStartDateStartingWith(UserEntity user, String startDate);

}
