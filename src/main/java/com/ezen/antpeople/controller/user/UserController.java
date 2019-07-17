package com.ezen.antpeople.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.antpeople.dto.user.UserDTO;
import com.ezen.antpeople.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("login")
	public String login(Model model) {
		logger.info("로그인 페이지");
		model.addAttribute("messege", "login.jsp 입니다");
		return "login/login";
	}
	
	@RequestMapping("check")
	public String loginCheck(String email, String password) throws Exception {
		logger.info("체크 페이지");			
//	public Model loginCheck(HttpServletRequest request, Model model) throws Exception {
		UserDTO userDto = new UserDTO();
		userDto.loginUser(email, password);
		String returnURL ="";
		if(userService.verifyPassword(userDto)) {
			logger.info("확인 성공 / 사용자 구분시작");			
			String userInfo = "1";					// DB에서 사용자 구분 가져와야함
			switch(userInfo) {
			case "1" : 
				logger.info("사용자 : 사장");
				returnURL = "owner/ownerMain";			// 사용자 별 URL 변경 필요
				break;
			case "2" :
				logger.info("사용자 : 알바");
				returnURL = "staff/staffMain";			// 사용자 별 URL 변경 필요
				break;
			default :	// 오류 발생시 로그인전으로 
				logger.info("사용자 구분 실패. 로그인 전으로");
				returnURL = "login/login";
				break;
			}	
		} else {
			logger.info("둘 다 틀림 / login으로");
			returnURL ="login/login";
		}
		return returnURL;
	}
	
//	True, False값으로 로그인여부 결정하기
	@RequestMapping("loginCheck")
	public String checking(boolean state) {
	
		String returnURL = "";
		if(state) {
			returnURL = "owner/ownerMain";
		} else {
			returnURL = "login";
		}
		return returnURL;
	}
	

}