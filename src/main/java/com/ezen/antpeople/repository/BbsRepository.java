package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.BbsEntity;

@Repository("Bbs")
public interface BbsRepository extends JpaRepository<BbsEntity, Integer>{
	public List<BbsEntity> findAllByOrderByIdDesc();
	public List<BbsEntity> findTop5ByOrderByIdDesc();
}	
