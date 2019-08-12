package com.ezen.antpeople.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.todo.TodoUserDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.entity.TodoEntity;
import com.ezen.antpeople.entity.TodoRelation;
import com.ezen.antpeople.entity.UserEntity;
import com.ezen.antpeople.repository.TodoRepository;
import com.ezen.antpeople.repository.UTRepository;
import com.ezen.antpeople.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	TodoRepository todoRepository;
	UTRepository utRepository;
	
	public TodoServiceImpl(TodoRepository todoRepository,UTRepository utRepository) {
		this.todoRepository = todoRepository;
		this.utRepository = utRepository;
	}

	//할일 생성 함수
	@Override
	public void uploadTodo(TodoDetailDTO todo) {
		log.info("할일 생성");
		TodoEntity entity = new TodoEntity(todo);
		todoRepository.save(entity);
	}

	//할일 삭제 함수
	@Override
	public void deleteTodo(int todo_id) {
		Optional<TodoEntity> entity = todoRepository.findById(todo_id);
		todoRepository.delete(entity.get());
	}
	
	//자신이 받은 할 일 확인
	@Override
	public void checkTodo(int todo_id) {
		Optional<TodoEntity> entity = todoRepository.findById(todo_id);
		TodoRelation todo = utRepository.findByTodo_id(todo_id);
		entity.get().downCheckPerson();
		todoRepository.save(entity.get()); //할일을 확인한 사람의 수를 확인
		todo.checkTodo();
		utRepository.save(todo); //할일 확인 기록
		
	}
	
	//자신이 보낸 할 일 리스트 
	@Override
	public List<TodoDetailDTO> TodoListByUser(UserDetailDTO user) {
		List<TodoEntity> entitys = todoRepository.findByFromUser_id(user.getUser_id());
		List<TodoDetailDTO> todoList = new ArrayList<TodoDetailDTO>();
		for(TodoEntity entity : entitys)
			todoList.add(entity.buildDTO());
		return todoList;
	}
	
	//자신이 받아야 하는 할 일 리스트 
	@Override
	public List<TodoUserDTO> TodoListByToUser(UserDetailDTO user) {
		List<TodoRelation> todoRelations = utRepository.findByToUser(new UserEntity(user));
		List<TodoUserDTO> todoList = new ArrayList<TodoUserDTO>();
		for(TodoRelation todoRelation : todoRelations)
			todoList.add(todoRelation.buildDTO());
		return todoList;
	}



}
