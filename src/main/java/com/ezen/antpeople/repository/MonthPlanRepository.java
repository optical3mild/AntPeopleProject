package com.ezen.antpeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.MonthPlanEntity;

@Repository
public interface MonthPlanRepository extends JpaRepository<MonthPlanEntity, Integer> {
	MonthPlanEntity findByUser_idAndMonthStartingWith(int user_id, String month);
	List<MonthPlanEntity> findByUserStoreStore(String store);
}
