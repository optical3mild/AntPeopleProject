package com.ezen.antpeople.controller.owner;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.user.RoleDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.UserService;


@Controller
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	UserService userService;
	
	public OwnerController(UserService userService) {
		this.userService = userService;
	}
	
	// 직원 전체 정보 목록
	@RequestMapping("staffinfo")
	public ModelAndView staffInfo(ModelAndView mv) throws Exception {
		logger.info("staffInfo 페이지");
		List<UserDetailDTO> userList = new ArrayList(userService.findByRole(new RoleDTO(102,"")));
		mv.addObject("userList", userList);
		mv.setViewName("staffinfo");
		return mv;
	}
	
	//----------------------- 근무 일정 페이지 ---------------------------
	// 근무 일정 계획
	@RequestMapping("planning")
	public String planning() throws Exception {
		logger.info("todayStaff 페이지");
		return "planning";
	}
	
	// 근무 승인
	@RequestMapping("accept")
	public String accept() throws Exception {
		logger.info("accept 페이지");
		return "accept";
	}
}
