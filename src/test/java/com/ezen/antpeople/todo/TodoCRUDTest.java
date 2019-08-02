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
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class TodoCRUDTest {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TodoService ts;
	
	
	//할일 추가 테스트 
	@Test @Ignore
	public void insertTodoTest() {
		UserDetailDTO user = new UserDetailDTO(1,"");
		List<UserDetailDTO> userList = new ArrayList<UserDetailDTO>();
		userList.add(new UserDetailDTO(3,""));
		userList.add(new UserDetailDTO(4,""));
		log.info("사용자 리스트 생성");
		TodoDetailDTO todo = new TodoDetailDTO("할일 테스트3 - 3에게",user,userList);
		ts.uploadTodo(todo);
		
	}
	

}
