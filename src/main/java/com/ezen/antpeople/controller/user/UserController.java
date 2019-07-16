package com.ezen.antpeople.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.antpeople.dto.UserDTO;
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
		} else if ("admin".equals(id) && !"welcome".equals(password)) {
			logger.info("비밀번호틀림 / login으로");
			returnURL ="login/login";
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
	@RequestMapping("/login.do")
	public String login(Model model) {
		logger.info("로그인 페이지");
		model.addAttribute("messege", "login.jsp 입니다");
		return "login";
	}
	
	@RequestMapping(value="goWork.do")
	public Model goWork(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();			// 세션을 가져옴
		UserDTO dto = new UserDTO();						
		boolean isSuccess = false;							// 성공여부
		try{
			if(session.getAttribute("userid") != null){		// 세션이 null일경우 String 으로 변환 안됨(Exception 발생)
				String userid = (String) session.getAttribute("userid");		// 세션에 저장한 userid 가져옴
				dto.setId(userid);												// dto에 유저아이디를 저장함
				userService.saveGo(dto);										// userService 에 saveGo 를 실행함
				isSuccess = true;												// 결과값 = 성공
			}
			model.addAttribute("result", isSuccess);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return model;
	}
	
	@RequestMapping(value="outWork.do")
	public Model outWork(HttpServletRequest request, Model model) throws Exception{
		HttpSession session = request.getSession();			// 세션을 가져옴
		UserDTO dto = new UserDTO();						
		boolean isSuccess = false;							// 성공여부
		try{
			if(session.getAttribute("userid") != null){		// 세션이 null일경우 String 으로 변환 안됨.(Exception 발생)
				String userid = (String) session.getAttribute("userid");		// 세션에 저장한 userid 가져옴
				dto.setId(userid);												// dto에 유저아이디를 저장함
				userService.saveOut(dto);										// userService 에 saveGo 를 실행함
				isSuccess = true;												// 결과값 = 성공
			}
			model.addAttribute("result", isSuccess);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return model;
	}

}