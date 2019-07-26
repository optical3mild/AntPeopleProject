package com.ezen.antpeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.ScheEntity;

@Repository("schedule")
public interface ScheRepository extends JpaRepository<ScheEntity, Long> {
	

}
