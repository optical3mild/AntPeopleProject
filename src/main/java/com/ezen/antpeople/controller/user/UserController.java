package com.ezen.antpeople.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/")
	public String main(Model model) {
		logger.info("Home 페이지");
		model.addAttribute("messege", "Home.jsp 입니다");
		return "home";
	}
	
	
	@RequestMapping("main")
	public String loginCheck(String id, String password) {
		logger.info("로그인체크 / id : " + id + " / password : " + password);
		String returnURL ="";
		if("admin".equals(id) && "welcome".equals(password)) {
			logger.info("로그인 성공 / main으로");
			returnURL ="/main";
		} else if ("admin".equals(id) && !"welcome".equals(password)) {
			logger.info("비밀번호틀림 / login으로");
			returnURL ="/login";
		} else if (!"admin".equals(id)) {
			logger.info("아이디가 존재하지 않습니다 / login으로");
			returnURL ="/login";
		} else {
			logger.info("둘 다 틀림 / login으로");
			returnURL ="/login";
		}
		return returnURL;
	}
	
//	@RequestMapping("/main.do")
//	public String loginCheck(Model model) {
//		logger.info("로그인 체크");
//		return "main";
//	}
	
	@RequestMapping("/login.do")
	public String login(Model model) {
		logger.info("로그인 페이지");
		return "login";
	}

}