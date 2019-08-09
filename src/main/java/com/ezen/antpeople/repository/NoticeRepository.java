package com.ezen.antpeople.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.NoticeEntity;

@Repository("Notice")
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer>{
	public List<NoticeEntity> findAllByOrderByIdDesc();
	public List<NoticeEntity> findTop5ByOrderByIdDesc();
}	
