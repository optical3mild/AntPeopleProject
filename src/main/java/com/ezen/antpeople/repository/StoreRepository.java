package com.ezen.antpeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>{
	StoreEntity findByStore(String store);
}
