package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.sche.ScheUserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_sche")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ScheRelation implements Serializable{
	
	@Id
	private int relation_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity toUser;
	
	@ManyToOne
	@JoinColumn(name="sche_id")
	private ScheEntity sche;
	
	private int state;
	
	public ScheUserDTO buildDTO() {
		return new ScheUserDTO(this.toUser.buildDTOSmall(), this.sche.getId(), this.state);
	}
	
	
	

}
