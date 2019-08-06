package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
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
	private String start_time;
	private String end_time;
	
	public ScheRelation(ScheUserDTO userToSche) {
		this.toUser = new UserEntity(userToSche.getUser());
		this.sche = new ScheEntity(userToSche.getSchedule_id(),"");	
		this.state = userToSche.getState();
		this.start_time = userToSche.getStartTime();
		this.end_time = userToSche.getEndTime();
	}
	
	//새로운 스케줄 등록
	public ScheRelation(UserEntity user, ScheEntity schedule) {
		this.toUser = user;
		this.sche = schedule;	
		this.state = 1; //처음 등록하는 스케줄은 무조건 신청 상태
	}
	
	public ScheUserDTO buildDTO() {
		return new ScheUserDTO(this.toUser.buildDTOSmall(), this.sche.getId(),this.sche.getUnique(),this.state);
	}
	
	public ScheUserDTO buildWorkDTO() {
		return new ScheUserDTO(this.toUser.buildDTOSmall(), this.sche.getId(),this.sche.getUnique(),this.state, this.start_time, this.end_time);
	}
	
	//ScheDetailDTO로 변환하는 메소드
	public ScheDetailDTO buildDetailDTO() {
		return this.sche.buildDTO();
	}
}
