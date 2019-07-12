package com.ezen.antpeople.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("login")
	public String login(Model model) {
		logger.info("로그인 페이지");
		model.addAttribute("messege", "login.jsp 입니다");
		return "login/login";
	}
	
	@RequestMapping("check")
	public String loginCheck(String id, String password) throws Exception {
//	public Model loginCheck(HttpServletRequest request, Model model) throws Exception {
//		HttpSession session = request.getSession();
//		UserDTO userDto = new userDto();
		logger.info("로그인체크 / id : " + id + " / password : " + password);
		String returnURL ="";
		if("admin".equals(id) && "welcome".equals(password)) {	// DB확인 후 아이디 존재여부 확인 추가필요
//		if(session.getAttribute("userId") != null) {
//			String userId = (String) session.getAttribute("userId");
//			userDto.setId(userId)'
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
		state = true;
		String returnURL = "";
		if(state) {
			returnURL = "owner/ownerMain";
		} else {
			returnURL = "login";
		}
		return returnURL;
	}
	

}