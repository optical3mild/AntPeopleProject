package com.ezen.antpeople.dto.todo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.Getter;

@Getter
public class TodoDetailDTO implements Serializable{
	
	private int todo_id;
	private String description;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	private boolean state;
	private UserDetailDTO fromUser;
	private List<UserDetailDTO> toUsers;
	
	public TodoDetailDTO() {}
	
	//할 일 만들기
	public TodoDetailDTO(String description, UserDetailDTO fromUser, List<UserDetailDTO> toUsers) {
		this.description = description;
		this.fromUser = fromUser;
		this.toUsers = toUsers;
	}
	
	//할일 불러오기
	public TodoDetailDTO(int todo_id, String description, boolean state, LocalDateTime updatedAt, UserDetailDTO fromUser, List<UserDetailDTO> toUsers) {
		this.todo_id = todo_id;
		this.description = description;
		this.state = state;
		this.updatedAt = updatedAt;
		this.fromUser = fromUser;
		this.toUsers = toUsers;
	}
	

}
