package com.ezen.antpeople.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// 직원 전체 정보 목록
	@RequestMapping("staffinfo")
	public String staffinfo() throws Exception {
		logger.info("staffInfo 페이지");
		return "staffinfo";
	}
	
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
