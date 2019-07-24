﻿package com.ezen.antpeople.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.user.RoleDTO;
import com.ezen.antpeople.dto.user.StoreDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.dto.user.UserLoginDTO;
import com.ezen.antpeople.service.UserService;

@Controller("user")
@SessionAttributes("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("index")
	public String loginIndex(Model model) {
		logger.info("로그인 페이지");
		return "login";
	}

	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String login(Model model) {
		logger.info("로그인 페이지");
		return "login";
	}

	@RequestMapping("usersuccess")
	public String loginSuccess(Model model) {
		logger.info("로그인 성공 페이지");
		return "${path}/main/guestmain";
	}

	// 로그인시 아이디 비밀번호 존재여부 체크
	@RequestMapping(value = "logincheck", method = RequestMethod.POST)
	@ResponseBody
	public String logincheck(@RequestBody UserLoginDTO user, Model model)
			throws Exception {
		logger.info("체크 페이지");
		UserDetailDTO userCheck = userService.verifiedPassword(user);
		if (userCheck != null) {
			logger.info("로그인 성공");
			model.addAttribute("user", userCheck);
			model.addAttribute("msg", "로그인 성공");
			return "../main/guestmain";
		} else {
			logger.info("로그인 실패");
			model.addAttribute("msg", "로그인 실패");
			return "redirection";
		}
	}

	// 회원가입 페이지로 이동 - 완료
	@RequestMapping("register")
	public String registerPage(Model model) throws Exception {
		logger.info("회원가입 페이지");
		List<RoleDTO> roles = new ArrayList(userService.RoleList());
		List<StoreDTO> stores = new ArrayList(userService.StoreList());
		model.addAttribute("roleList", roles);
		model.addAttribute("storeList", stores);
		return "register";
	}
	
	  // 회원가입 
	
	  @RequestMapping(value="registercheck", method= RequestMethod.POST)
	  @ResponseBody 
	  public String registerCheck(@RequestBody UserDetailDTO user, Model model) throws Exception{
		  String msg = userService.userSignUp(user);
		  model.addAttribute("response", msg);
		  return "register";
	  }

	// 출근
//	@RequestMapping(value = "goWork.do")
//	public Model goWork(HttpServletRequest request, Model model) throws Exception {
//		HttpSession session = request.getSession(); // 세션을 가져옴
//		UserDetailDTO userDetail = new UserDetailDTO();
//		boolean isSuccess = false; // 성공여부
//		try {
//			if (session.getAttribute("user_id") != null) { // 세션이 null일경우 String 으로 변환 안됨(Exception 발생)
//				int user_id = (int) session.getAttribute("user_id"); // 세션에 저장한 user_id 가져옴
//				userDetail.setId(user_id); // dto에 유저아이디를 저장함
//				userService.saveGo(userDetail); // userService 에 saveGo 를 실행함
//				isSuccess = true; // 결과값 = 성공
//			}
//			model.addAttribute("result", isSuccess);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return model;
//	}

	// 퇴근
//	@RequestMapping(value = "outWork.do")
//	public Model outWork(HttpServletRequest request, Model model) throws Exception {
//		HttpSession session = request.getSession(); // 세션을 가져옴
//		UserDetailDTO userDetail = new UserDetailDTO();
//		boolean isSuccess = false; // 성공여부
//		try {
//			if (session.getAttribute("user_id") != null) { // 세션이 null일경우 String 으로 변환 안됨.(Exception 발생)
//				int user_id = (int) session.getAttribute("user_id"); // 세션에 저장한 user_id 가져옴
//				userDetail.setId(user_id); // dto에 유저아이디를 저장함
//				userService.saveOut(userDetail); // userService 에 saveGo 를 실행함
//				isSuccess = true; // 결과값 = 성공
//			}
//			model.addAttribute("result", isSuccess);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return model;
//	}

}