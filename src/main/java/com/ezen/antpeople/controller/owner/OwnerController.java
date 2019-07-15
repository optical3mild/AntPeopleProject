package com.ezen.antpeople.controller.owner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.controller.user.UserController;
import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.serviceimpl.UserServiceImpl;

@Controller
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("owner") // UserController check 에서 경로를 "owner"로 수정 할것
	public ModelAndView staffList(ModelAndView mav, int id) throws Exception {
		List<UserDTO> userList = UserServiceImpl.getUserList(id);
		
	return "ownerMain";
	}
}
