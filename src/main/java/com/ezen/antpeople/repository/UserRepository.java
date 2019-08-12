package com.ezen.antpeople.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.ezen.antpeople.entity.RoleEntity;
import com.ezen.antpeople.entity.StoreEntity;
import com.ezen.antpeople.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	@Nullable
	Optional<UserEntity> findByEmail(String email);
	
	List<UserEntity> findByRole(RoleEntity role);
	List<UserEntity> findByRoleAndStore(RoleEntity role, StoreEntity Store);
	List<UserEntity> findByStoreStore(String store);
	
}
