package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ezen.antpeople.dto.user.RoleDTO;

import lombok.Getter;

@Getter
@Entity
@Table(name="role")
//RoleDB - role_id, role
public class RoleEntity implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int role_id;
	
	@Column(name="role")
	private String role;
	
	public RoleEntity() {}
	
	public RoleEntity(RoleDTO role) {
		this.role_id = role.getRole_id();
		this.role = role.getRole();
	}
	
	public RoleDTO buildDTO() {
		return new RoleDTO(this.role_id,this.role);
	}
	
	
}
