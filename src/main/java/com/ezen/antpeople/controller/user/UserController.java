package com.ezen.antpeople.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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

	// 로그인 페이지로 이동
	@RequestMapping("login")
	public String loginIndex(Model model) {
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
	public String logincheck(@RequestBody UserLoginDTO userLogin, Model model)
			throws Exception {
		logger.info("체크 페이지");
		UserDetailDTO user = userService.findByEmail(userLogin.getEmail());
		if (userService.verifiedPassword(user,userLogin.getPassword())) {
			logger.info("로그인 성공");
			model.addAttribute("user", user);
			model.addAttribute("mmm", "로그인 성공");
			return "../main/mainpage";
		} else {
			logger.info("로그인 실패");
			model.addAttribute("mmm", "로그인 실패");
			return "redirection";
		}
	}
	//로그아웃
	@RequestMapping("logout")
	public ModelAndView logout(SessionStatus sessionStatus, ModelAndView mv) {
		logger.info("세션 제거");
		sessionStatus.setComplete();
        mv.setViewName("redirect:../main/mainpage");
        return mv;
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
		  model.addAttribute("message", msg);
		  return "login";
	  }

}