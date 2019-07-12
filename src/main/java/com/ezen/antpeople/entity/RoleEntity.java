package com.ezen.antpeople.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name="role")

public class RoleEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int role_id;
	
	@Column(name="role")
	private String role;
}
