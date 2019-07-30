package com.ezen.antpeople.todo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class TodoCRUDTest {
	
	@Source
	TodoService ts;
	
	@Test
	public void insertTodoTest() {
		UserDetailDTO user = new UserDetailDTO(1,"");
		List<UserDetailDTO> userList = new ArrayList();
		userList.add(new UserDetailDTO(1,""));
		userList.add(new UserDetailDTO(2,""));
		userList.add(new UserDetailDTO(3,""));
		System.out.println("리스트 준비");
		ts.uploadTodo(new TodoDetailDTO("할일 테스트",user,userList));
		
	}
	

}
