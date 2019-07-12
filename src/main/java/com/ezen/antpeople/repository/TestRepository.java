package com.ezen.antpeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezen.antpeople.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {

}
