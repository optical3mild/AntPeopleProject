package com.ezen.antpeople.dto.todo;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoListDTO {
	private List<TodoDetailDTO> sendList;
	private List<TodoUserDTO> receiveList;
	
	public TodoListDTO(List<TodoDetailDTO> sendList, List<TodoUserDTO> receiveList) {
		this.sendList = sendList;
		this.receiveList = receiveList;
	}
	
	public String toString() {
		return "{\"forReceievedList\" : " + this.receiveList + ",\"forSendList\" : " +  this.sendList+"}"; 
	}

}
