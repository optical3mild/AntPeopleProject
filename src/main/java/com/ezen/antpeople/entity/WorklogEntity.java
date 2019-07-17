package com.ezen.antpeople.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.UserDTO;

@Entity
@Table(name="worklog")
public class WorklogEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	@Column(nullable=false)
	private String godate;
	@Column(nullable=false)
	private String outdate;
	
	
	
	public String getGodate() {
		return godate;
	}
	public void setGodate(String godate) {
		this.godate = godate;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public UserDTO buildDomain() {
		UserDTO user = new UserDTO();
		return user;
	}
}
