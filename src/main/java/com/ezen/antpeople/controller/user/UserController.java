package com.ezen.antpeople.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/")
	public String main(Model model) {
		logger.info("Home 페이지");
		model.addAttribute("messege", "Home.jsp 입니다");
		return "home";
	}
	
	
	@RequestMapping("check")
	public String loginCheck(String id, String password) throws Exception {
		logger.info("로그인체크 / id : " + id + " / password : " + password);
		String returnURL ="";
		if("admin".equals(id) && "welcome".equals(password)) {	// DB확인 후 아이디 존재여부 확인 추가필요
			logger.info("확인 성공 / 사용자 구분시작");			
			String userInfo = "1";					// DB에서 사용자 구분 가져와야함
			switch(userInfo) {
			case "1" : 
				logger.info("사용자 : 사장");
				returnURL = "main";			// 사용자 별 URL 변경 필요
				break;
			case "2" :
				logger.info("사용자 : 알바");
				returnURL = "main";			// 사용자 별 URL 변경 필요
				break;
			default :	// 오류 발생시 로그인전으로 
				logger.info("사용자 구분 실패. 로그인 전으로");
				returnURL = "login";
				break;
			}	
		} else if ("admin".equals(id) && !"welcome".equals(password)) {
			logger.info("비밀번호틀림 / login으로");
			returnURL ="login";
		} else {
			logger.info("둘 다 틀림 / login으로");
			returnURL ="login";
		}
		return returnURL;
	}
	
	@RequestMapping("/login.do")
	public String login(Model model) {
		logger.info("로그인 페이지");
		model.addAttribute("messege", "login.jsp 입니다");
		return "login";
	}

}