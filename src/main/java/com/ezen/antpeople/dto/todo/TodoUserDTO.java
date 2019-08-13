package com.ezen.antpeople.dto.todo;

import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoUserDTO {
	private UserDetailDTO toUser;
	private TodoDetailDTO todo;

	private boolean state;
	
	public TodoUserDTO(UserDetailDTO toUser,TodoDetailDTO todo, boolean state) {
		this.toUser = toUser;
		this.todo = todo;
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\""+todo.getId()+"\","
				+ "\"description\":\""+todo.getDescription()+"\","
				+ "\"fromUser\":"+todo.getFromUser()+ ","
				+ "\"state\":\"" + state +"\"}";
	}

}
