package com.ezen.antpeople.dto.sche;

import java.time.LocalDateTime;
import java.util.List;

import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.Getter;

@Getter
public class ScheDetailDTO {
	
	private int sche_id;
	private String id;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String title;
	private int state;
	private int manPower;
	private int peopleCount;
	private UserDetailDTO fromUser;
	private List<UserDetailDTO> toUsers;
	
	public ScheDetailDTO() {}

	//일정 정보 등록시
	public ScheDetailDTO(int id, String sche_unique, LocalDateTime createdAt, LocalDateTime updatedAt,
			String startDate, String endDate, String startTime, String endTime, String title,int state, int manPower,
			int peopleCount, UserDetailDTO fromUser, List<UserDetailDTO> toUsers) {
		super();
		this.sche_id = id;
		this.id = sche_unique;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.state = state;
		this.manPower = manPower;
		this.peopleCount = peopleCount;
		this.fromUser = fromUser;
		this.toUsers = toUsers;
	}
	
	public ScheDetailDTO( String sche_unique, String startDate, String endDate, String startTime, String endTime, String title,int state, int manPower,
			UserDetailDTO fromUser) {
		super();
		this.id = sche_unique;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.title = title;
		this.state = state;
		this.manPower = manPower;
		this.fromUser = fromUser;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", "
				+ "\"startDate\":\"" + startDate + "\","
				+ "\"endDate\":\"" + endDate + "\","
				+ "\"startTime\":\"" + startTime + "\","
				+ "\"endTime\":\"" + endTime + "\","
				+ "\"title\":\"" + title + "\","
				+ "\"state\":" + state + ","
				+ "\"manPower\":" + manPower + ","
				+ "\"userId\":" + fromUser.getUser_id()
				+ "}";
	}

}
