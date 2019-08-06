package com.ezen.antpeople.controller.staff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.sche.MonthPlanDTO;
import com.ezen.antpeople.dto.sche.ScheDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserListDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.MonthPlanService;
import com.ezen.antpeople.service.ScheService;
import com.ezen.antpeople.service.UserService;

@Controller
public class StaffController {
	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);
	
	UserService userService;
	ScheService scheService;
	MonthPlanService monthplanService;
	
	public StaffController(UserService userService, ScheService scheService, MonthPlanService monthplanService) {
		this.userService = userService;
		this.scheService = scheService;
		this.monthplanService = monthplanService;
	}
	
	// ---------------------------------- 근무 신청 -----------------------------------------
	//근무신청 페이지로 이동
	@RequestMapping("requestwork")
	@ResponseBody
	public ModelAndView goRequestwork(ModelAndView mav, HttpServletRequest request) throws Exception {
		logger.info("requestWork");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO user = (UserDetailDTO) httpSession.getAttribute("user");
		int date = Integer.parseInt(LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("yyMM")))-1;
		String sche = monthplanService.monthPlanList(user);
		logger.info("user : "+user);
		logger.info("monthIndex : "+ date);
		mav.addObject("monthIndex", sche);
		mav.setViewName("requestwork");
		return mav;
	}
	
	// 월 클릭시 클릭한 월 받아서 해당 데이터 보여줌
	@RequestMapping(value="monthlist", method = RequestMethod.POST)	// monthplanpage에 있는 monthplan(월별 리스트)를 클릭함
	@ResponseBody
	public String monthList(@RequestBody String date, HttpServletRequest request, ScheUserListDTO schedules) throws Exception {
		logger.info("clickMonth");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		schedules = scheService.findAllMonthAndStaff(userDto, date);
		logger.info(schedules.toString());
		return schedules.toString();	// 클릭한 월의 데이터를 보냄
	}

	//새로운 일정에 근무 신청 시
	@RequestMapping("applyschedule")
	@ResponseBody
	public String applySchedule(HttpServletRequest request, @RequestBody String schedule_id) throws Exception {
		logger.info("근무신청");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO user = (UserDetailDTO) httpSession.getAttribute("user");
		String schedule = scheService.updateUserSchedule(user, schedule_id);
		logger.info("schedule_id : "+schedule_id);
		logger.info("schedule : "+schedule);
		return schedule;
	}
	
	
	//신청한 근무 일정 취소 시 
	@RequestMapping("refuseschedule")
	@ResponseBody
	public String refuseSchedule(HttpServletRequest request, @RequestBody String schedule_id) throws Exception {
		logger.info("근무신청 취소");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO user = (UserDetailDTO) httpSession.getAttribute("user");
		String schedule = scheService.deleteSchedule(user, schedule_id);
		logger.info("schedule_id : "+schedule_id);
		logger.info("schedule : "+schedule);
		return schedule;
	}
	
	
	// -------------------------------- 근무 수정  -----------------------------------------
	// 근무 수정 페이지로
	@RequestMapping("modifywork")
	public ModelAndView modifywork(ModelAndView mav,  HttpServletRequest request) throws Exception {

		return mav;
	}
	
	// 근무 수정완료 버튼 클릭 시
	@RequestMapping("modifymonthplan")
	@ResponseBody
	public ModelAndView modifyworking(ModelAndView mav, @RequestBody Map<String, ScheDetailDTO> schedules) throws Exception {
		logger.info("근무 수정");

		return mav;
	}
	
	// -------------------------------- 출 퇴근 -----------------------------------------------------
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
