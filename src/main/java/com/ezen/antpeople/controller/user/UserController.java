package com.ezen.antpeople.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.user.UserDTO;
import com.ezen.antpeople.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/pages/login.do")
	public String login(Model model) {
		logger.info("로그인 페이지");
		return "login/login";
	}

	@RequestMapping(value="/pages/logincheck", method = RequestMethod.POST)
	public String logincheck(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
		logger.info("체크 페이지");			
//	public Model logincheck(HttpServletRequest request, Model model) throws Exception {
		UserDTO userDto = new UserDTO();
		userDto.loginUser(email, password);
		String returnURL ="";
		if(userService.verifyPassword(userDto)) {
			logger.info("확인 성공 / 사용자 구분시작");			
			returnURL = "/common/notice";
		} else {
			returnURL = "/pages/login";
		}
		return returnURL;
	}
	
	@RequestMapping("pages/register")
	public String register() throws Exception {
		return "pages/register";
	}
	
	@RequestMapping(value="/pages/register.do", method= RequestMethod.POST)
	public String registerPOST(UserDTO userDto) throws Exception {
		userService.saveUser(userDto);
		return "pages/login";
	}

	// 출퇴근 기능
	@RequestMapping(value = "goWork.do")
	public Model goWork(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession(); // 세션을 가져옴
		UserDTO dto = new UserDTO();
		boolean isSuccess = false; // 성공여부
		try {
			if (session.getAttribute("user_id") != null) { // 세션이 null일경우 String 으로 변환 안됨(Exception 발생)
				String user_id = (String) session.getAttribute("user_id"); // 세션에 저장한 user_id 가져옴
				// dto.setId(user_id); // dto에 유저아이디를 저장함
				userService.saveGo(dto); // userService 에 saveGo 를 실행함
				isSuccess = true; // 결과값 = 성공
			}
			model.addAttribute("result", isSuccess);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "outWork.do")
	public Model outWork(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession(); // 세션을 가져옴
		UserDTO dto = new UserDTO();
		boolean isSuccess = false; // 성공여부
		try {
			if (session.getAttribute("user_id") != null) { // 세션이 null일경우 String 으로 변환 안됨.(Exception 발생)
				String user_id = (String) session.getAttribute("user_id"); // 세션에 저장한 user_id 가져옴
				// dto.setId(user_id); // dto에 유저아이디를 저장함
				userService.saveOut(dto); // userService 에 saveGo 를 실행함
				isSuccess = true; // 결과값 = 성공
			}
			model.addAttribute("result", isSuccess);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

}