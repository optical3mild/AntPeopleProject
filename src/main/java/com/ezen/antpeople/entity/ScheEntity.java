package com.ezen.antpeople.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="schedule")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AttributeOverride(name = "id", column = @Column(name = "sche_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "created_time"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_time"))
@Getter
public class ScheEntity extends BaseEntity implements Serializable {
	
	private String sche_unique;
	
	@Column(name="start_date")
	private String startDate;
	@Column(name="end_date")
	private String endDate;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
	private String title;
	private int state;
	private int manPower;
	private int peopleCount;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_sche", joinColumns = @JoinColumn(name="sche_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> toUsers;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity fromUser;
	
	//일정 정보 등록
	public ScheEntity(ScheDetailDTO schedule) {
		
		List<UserEntity> userList = new ArrayList<UserEntity>();
		for(UserDetailDTO user: schedule.getToUsers())
			userList.add(new UserEntity(user));
		
		
		this.id = schedule.getSche_id();
		this.sche_unique = schedule.getId();
		this.startDate = schedule.getStartDate();
		this.endDate = schedule.getEndDate();
		this.startTime = schedule.getStartTime();
		this.endTime = schedule.getEndTime();
		this.title = schedule.getTitle();
		this.state = schedule.getState();
		this.manPower = schedule.getManPower();
		this.fromUser = new UserEntity(schedule.getFromUser());
		this.toUsers = userList;
		
	}
	
	//일정 상세 정보 내보내기
	public ScheDetailDTO buildDTO() {
		List<UserDetailDTO> toUsersDTO = new ArrayList();
		for(UserEntity user : this.toUsers)
			toUsersDTO.add(user.buildDTO());
		return new ScheDetailDTO(this.id, this.sche_unique,this.createdAt, this.updatedAt, this.startDate, this.endDate, this.startTime, this.endTime, this.title, this.state, this.manPower,this.peopleCount, this.fromUser.buildDTO(),toUsersDTO );
	}

}
