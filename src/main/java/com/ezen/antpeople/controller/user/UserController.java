package com.ezen.antpeople.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	// 로그인시 아이디 비밀번호 존재여부 체크
	@RequestMapping(value = "logincheck", method = RequestMethod.POST)
	@ResponseBody
	public String logincheck(@RequestBody UserLoginDTO userLogin, Model model, HttpSession session, HttpServletResponse response)
			throws Exception {
		logger.info("체크 페이지");
		UserDetailDTO user = userService.findByEmail(userLogin.getEmail());
		if (userService.verifiedPassword(user,userLogin.getPassword())) {
			logger.info("로그인 성공");
			model.addAttribute("user", user);
			model.addAttribute("mmm", "로그인 성공");
//			if ( user.isUseCookie() ){ // dto 클래스 안에 useCookie 항목에 폼에서 넘어온 쿠키사용 여부(true/false)
//                // 쿠키 사용이 체크되어 있으면
//                // 쿠키를 생성하고 현재 로그인되어 있을 때 생성되었던 세션의 id를 쿠키에 저장
//                Cookie cookie = new Cookie("loginCookie", session.getId());
//                // 쿠키를 찾을 경로를 컨텍스트 경로로 변경
//                cookie.setPath("/");
//                cookie.setMaxAge(60*60*24*7); // 유효시간 7일
//                response.addCookie(cookie);
//            }
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