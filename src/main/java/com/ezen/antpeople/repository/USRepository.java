package com.ezen.antpeople.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.ScheRelation;

@Repository()
public interface USRepository extends JpaRepository<ScheRelation, Integer>{
	List<ScheRelation> findBySche_id(Integer sche_id); //
	List<ScheRelation> findByToUserStoreStoreAndScheFromUserIdAndStateAndScheStartDateStartingWith(String store,int userId,int state,String month);
	List<ScheRelation> findByToUser_idAndScheStartDateStartingWith(int userId,String month);
	
	//당일 근무자 찾기
	List<ScheRelation> findByToUserStoreStoreAndScheStartDate(String store, String month);
	
	//상태에 따른 근무일정 찾기 
	List<ScheRelation> findByToUser_idAndState(int user_id, int state);
	
	@Transactional
	void deleteBySche_unique(String unique);
	
}
