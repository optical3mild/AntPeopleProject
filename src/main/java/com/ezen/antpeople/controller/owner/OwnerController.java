package com.ezen.antpeople.controller.owner;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserListDTO;
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
	
	// 운영 계획 - 1
	@RequestMapping("monthplanpage")
	public ModelAndView monthplanpage(ModelAndView mav,  HttpServletRequest request) {
		logger.info("monthplanpage");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		Set<String> sche = scheService.isScheduleMonthList(userDto);
		logger.info("monthplanpage Data : "+sche.toString());
		mav.addObject("monthList", sche);
		mav.setViewName("monthplanpage");
		return mav;
	}
	
	// 클릭한 월 받아서 해당 데이터 넘겨주기 - 2
	@RequestMapping(value="monthplan", method = RequestMethod.POST)
	@ResponseBody
	public ScheUserListDTO monthplan(ModelAndView mav, @RequestBody String date, HttpServletRequest request, ScheUserListDTO schedules) throws Exception {
		logger.info("monthplan");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		schedules = scheService.findAllMonth(userDto.getUser_id(), date);
		logger.info("schedules" + schedules.toString());
		return schedules;
	}
	
//	// 생성버튼 3-1
//	@RequestMapping("insertplanpage")
//	@ResponseBody
//	public String insertPlanPage(@RequestBody String month, Model model) throws Exception {
//		logger.info("insertplanpage");
//		model.addAttribute("month", month);
//		return "planning";
//	}
//	
//	// 생성 3-1-1
//	@RequestMapping("insertplan")
//	@ResponseBody
//	public String insertPlan(@RequestBody Map<String, ScheDetailDTO> schedules) throws Exception {
//		logger.info("insertplan");
//		scheService.saveSchedules(schedules);
//		return "monthplanpage";
//	}
//	
//	
//	// 수정 버튼  3-2
//	@RequestMapping("updateplanpage")
//	@ResponseBody
//	public String updatePlanPage(Model model, @RequestBody String month, HttpServletRequest request) throws Exception {
//		HttpSession httpSession = request.getSession(true);
//		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
//		int user_id = userDto.getUser_id();
//		model.addAttribute("updateplan", scheService.findAllMonth(user_id, month));
//		return "planning";
//	}
//	
//	// 수정 완료 3-2-1
//	@RequestMapping("updateplan")
//	@ResponseBody
//	public String updatePlan(@RequestBody Map<String, ScheDetailDTO> schedules) throws Exception {
//		scheService.updateSchedule(schedules);
//		return "monthplanpage";
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
