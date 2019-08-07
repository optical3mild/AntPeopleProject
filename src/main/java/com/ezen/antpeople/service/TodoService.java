package com.ezen.antpeople.service;

import java.util.List;

import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;

public interface TodoService {
	public void uploadTodo(TodoDetailDTO todo); //할일 생성
	public void deleteTodo(int todo_id); //할일 삭제
	public List<TodoDetailDTO> TodoListByUser(UserDetailDTO user); //할일 리스트 받아오기
	public List<TodoDetailDTO> TodoListByFromUser(UserDetailDTO user);
}
