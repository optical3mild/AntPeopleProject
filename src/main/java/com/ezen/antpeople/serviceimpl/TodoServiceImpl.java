package com.ezen.antpeople.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.todo.TodoListDTO;
import com.ezen.antpeople.dto.todo.TodoUserDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.entity.TodoEntity;
import com.ezen.antpeople.entity.TodoRelation;
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
		utRepository.deleteByTodo_id(todo_id);
	}
	
	//자신이 받은 할 일 확인
	@Override
	public void checkTodo(int todo_id,int user_id) {
		Optional<TodoEntity> entity = todoRepository.findById(todo_id);
		TodoRelation todo = utRepository.findByTodo_idAndToUser_id(todo_id, user_id);
		entity.get().downCheckPerson();
		todoRepository.save(entity.get()); //할일을 확인한 사람의 수를 확인
		todo.checkTodo();
		utRepository.save(todo); //할일 확인 기록
		
	}
	
	//자신이 보낸 할 일 리스트 
	@Override
	public List<TodoDetailDTO> TodoListByUser(UserDetailDTO user) {
		log.info("자신이 작성한 할 일 리스트 받기");
		List<TodoEntity> entitys = todoRepository.findByFromUser_id(user.getUser_id());
		List<TodoDetailDTO> todoList = new ArrayList<TodoDetailDTO>();
		for(TodoEntity entity : entitys)
			todoList.add(entity.buildDTO());
		return todoList;
	}
	
	//자신이 받아야 하는 할 일 리스트 
	@Override
	public List<TodoUserDTO> TodoListByToUser(UserDetailDTO user) {
		log.info("user의 아이디 : " + user.getUser_id());
		List<TodoRelation> todoRelations = utRepository.findByToUser_id(user.getUser_id());
		List<TodoUserDTO> todoList = new ArrayList<TodoUserDTO>();
		for(TodoRelation todoRelation : todoRelations)
			todoList.add(todoRelation.buildDTO());
		return todoList;
	}
	
	// 자신이 보내고 받은 할 일 리스트 전부 받아오기
	public String TodoListAll(UserDetailDTO user) {
		TodoListDTO todoList = new TodoListDTO(TodoListByUser(user),TodoListByToUser(user));
		return todoList.toString();
	}



}
