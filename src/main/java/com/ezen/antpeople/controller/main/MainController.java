package com.ezen.antpeople.controller.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.antpeople.controller.user.UserController;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/index")
	public String main(Model model) {
		logger.info("index 페이지");
		return "login";
	}
	
}
