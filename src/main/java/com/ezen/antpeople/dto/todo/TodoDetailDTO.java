package com.ezen.antpeople.dto.todo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.dto.user.UserTodoDTO;

import lombok.Getter;

@Getter
public class TodoDetailDTO implements Serializable{
	
	private int id;
	private String description;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	private boolean state;
	private int checkPerson;
	private UserTodoDTO fromUser;
	private List<UserTodoDTO> toUsers;
	
	public TodoDetailDTO() {}
	
	//할 일 만들기
	public TodoDetailDTO(String description, UserTodoDTO fromUser, List<UserTodoDTO> toUsers) {
		this.description = description;
		this.fromUser = fromUser;
		this.toUsers = toUsers;
	}
	
	//할일 불러오기
	public TodoDetailDTO(int id, String description, boolean state, int checkPerson , LocalDateTime updatedAt, UserTodoDTO fromUser, List<UserTodoDTO> toUsers) {
		this.id = id;
		this.description = description;
		this.state = state;
		this.checkPerson = checkPerson;
		this.updatedAt = updatedAt;
		this.fromUser = fromUser;
		this.toUsers = toUsers;
	}
	
	public void fromUser(UserTodoDTO user) {
		this.fromUser = user;
	}
	public void toUsers(List<UserTodoDTO> users) {
		this.toUsers = users;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\""+id+"\","
				+ "\"description\":\""+description+"\","
				+ "\"checkPerson\":\""+checkPerson+"\"}";
	}
	

}
