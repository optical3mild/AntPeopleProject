package com.ezen.antpeople.controller.staff;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.controller.user.UserController;
import com.ezen.antpeople.dto.UserDTO;
import com.ezen.antpeople.service.UserService;

@Controller
public class StaffController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	UserService userService;
	
	@RequestMapping("owner") // UserController check 에서 경로를 "owner"로 수정 할것
	public ModelAndView staffList(ModelAndView mav, int id) throws Exception {
		UserDTO user = userService.getUser(id);
		mav.setViewName("pages/calender");
		mav.addObject("list", user);
		return mav;
	}
}
