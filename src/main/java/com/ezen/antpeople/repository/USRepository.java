package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.ScheRelation;

@Repository()
public interface USRepository extends JpaRepository<ScheRelation, Integer>{
	List<ScheRelation> findBySche_id(Integer sche_id);
}