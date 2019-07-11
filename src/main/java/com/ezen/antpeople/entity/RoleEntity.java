package com.ezen.antpeople.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name="role")
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class RoleEntity extends BaseEntity {
	
	@Column(name="role")
	private String role;
}
