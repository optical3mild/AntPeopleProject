package com.ezen.antpeople.todo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.todo.TodoUserDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class TodoCRUDTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TodoService ts;
	
	
	//할일 추가 테스트 - 성공
	@Test @Ignore
	public void insertTodoTest() {
		UserDetailDTO user = new UserDetailDTO(3,"");
		List<UserDetailDTO> userList = new ArrayList<UserDetailDTO>();
		userList.add(new UserDetailDTO(5,""));
		userList.add(new UserDetailDTO(6,""));
		log.info("사용자 리스트 생성");
		TodoDetailDTO todo = new TodoDetailDTO("할일 테스트5 - 5,6에게",user,userList);
		ts.uploadTodo(todo);
	}
	
	//본인이 작성한 할일 리스트 테스트 - 성공
	@Test @Ignore
	public void todoListTest() {
		log.info("자신이 작성한 할 일 리스트 가져오기");
		UserDetailDTO user = new UserDetailDTO(1,"");
		List<TodoDetailDTO> todoList = new ArrayList<TodoDetailDTO>(ts.TodoListByUser(user));
		log.info(todoList.toString());
		
	}
	
	//본인이 받은 할일 리스트 테스트 - 성공
	@Test @Ignore
	public void TodoListByToUserTest() {
		log.info("자신이 받은 할 일 리스트 가져오기");
		UserDetailDTO user = new UserDetailDTO(3,"");
		List<TodoUserDTO> todoList = new ArrayList<TodoUserDTO>(ts.TodoListByToUser(user));
		log.info(todoList.toString());
		
	}
	
	//본인이 보낸 할일 리스트와 받은 할 일 리스트 가져오기 테스트 - 성공
	@Test @Ignore
	public void TodoListAllTest() {
		log.info("자신이 보내고, 받은 할 일 리스트 가져오기");
		UserDetailDTO user = new UserDetailDTO(3,"");
		String todoList = ts.TodoListAll(user);
		log.info(todoList);
		
	}
	
	//본인이 받은 할일 리스트 확인 체크 - 성공
	@Test @Ignore
	public void TodoCheck() {
		ts.checkTodo(18,3);
	}
	
	//본인이 작성한 할 일 리스트 삭제 - 성공
	@Test
	public void deleteTodo() {
		ts.deleteTodo(18);
	}

}
