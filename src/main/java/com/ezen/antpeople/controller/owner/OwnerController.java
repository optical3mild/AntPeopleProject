package com.ezen.antpeople.controller.owner;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.user.RoleDTO;
import com.ezen.antpeople.dto.user.StoreDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.ScheService;
import com.ezen.antpeople.service.UserService;


@Controller
@SessionAttributes("user")
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	UserService userService;
	ScheService scheService;
	
	public OwnerController(UserService userService, ScheService scheService) {
		this.userService = userService;
		this.scheService = scheService;
	}
	
	// 직원 전체 정보 목록
	@RequestMapping("staffinfo")
	public ModelAndView staffInfo(ModelAndView mv,HttpServletRequest request) throws Exception {
		logger.info("staffInfo 페이지");
		//세션 받아오기
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		StoreDTO store = user.getStore();
		System.out.println(store.toString());
		List<UserDetailDTO> userList = new ArrayList(userService.findByStore(new RoleDTO(102,""),store));
		mv.addObject("userList", userList);
		mv.setViewName("staffinfo");
		return mv;
	}
	


	//----------------------- 근무 일정 페이지 ---------------------------
	// 운영 계획페이지로 이동
	@RequestMapping("planningpage")
	@ResponseBody
	public ModelAndView goPlanning(ModelAndView mav, HttpServletRequest request) throws Exception {
		logger.info("planning 페이지");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		Set<ScheDetailDTO> scheDetailList = scheService.findAllOnwer(userDto.getUser_id());
		logger.info(scheDetailList.toString());
		mav.addObject("jsonList", scheDetailList);
		mav.setViewName("planning");
		return mav;
	}
	

	// 운영 계획 저장
	@RequestMapping(value="createplan", method=RequestMethod.POST)
	@ResponseBody
	public String planning(@RequestBody Map<String, ScheDetailDTO> scheDto, Model model) throws Exception {
		logger.info("createplan");
		scheService.saveSchedules(scheDto);
	return "planningpage";
	}
	
//	// 운영 계획
//	@RequestMapping("planningpage")
//	public String showPlan(Model model, HttpServletRequest request, @RequestParam Map<String, ScheDetailDTO> scheDto) {
//		logger.info("OwnerCon - submitPlan");
//		HttpSession httpSession = request.getSession(true);
//		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
//		scheDto = scheService.findAllOnwer(userDto.getUser_id());
//		model.addAttribute("planereventdata", scheDto);
//		return "redirect:planning";
//	}
//	----------------------------- 승인 ---------------------------------------------
	// 승인페이지 이동
	@RequestMapping("acceptpage")
	public String goAccept() throws Exception {
		logger.info("accept 페이지");
		return "accept";
	}
	
//	// 근무 승인
//	@RequestMapping("accept")
//	@ResponseBody
//	public String accept(@RequestBody Map<String, ScheDetailDTO> scheDto, Model model) throws Exception {
//		logger.info("근무 승인");
//	#
//		return "../main/mainpage";
//	}
	
//	----------------------------- 금일 근무자 ---------------------------------------------
//	// 금일 근무자
//	@RequestMapping("todaystaff")
//	public String todayStaff(Model model) {
//		logger.info("todayStaff");
//		#
//		return "todaystaff";
//	}
	
}
