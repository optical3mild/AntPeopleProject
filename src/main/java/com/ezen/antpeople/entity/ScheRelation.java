package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.sche.ScheUserDTO;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_sche")
@IdClass(ScheRelationId.class)

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ScheRelation implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity toUser;
	
	@Id
	@ManyToOne
	@JoinColumn(name="sche_id")
	private ScheEntity sche;
	
	private int state;
	
	public ScheRelation(ScheUserDTO userToSche) {
		this.toUser = new UserEntity(userToSche.getUser());
		this.sche = new ScheEntity(userToSche.getSchedule_id(),"");	
		this.state = userToSche.getState();
	}
	public ScheUserDTO buildDTO() {
		return new ScheUserDTO(this.toUser.buildDTOSmall(), this.sche.getId(),this.sche.getUnique(),this.state);
	}
}
