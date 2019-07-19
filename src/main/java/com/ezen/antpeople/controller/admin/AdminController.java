package com.ezen.antpeople.controller.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping("staffinfo")
	public String staffinfo() throws Exception {
		logger.info("staffInfo 페이지");
		return "staffinfo";
	}
	
	@RequestMapping("planning")
	public String planning() throws Exception {
		logger.info("todayStaff 페이지");
		return "planning";
	}
	
	@RequestMapping("accept")
	public String accept() throws Exception {
		logger.info("accept 페이지");
		return "accept";
	}
}
