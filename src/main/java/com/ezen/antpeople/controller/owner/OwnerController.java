package com.ezen.antpeople.controller.owner;


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
import com.ezen.antpeople.dto.sche.ScheUserListDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.MonthPlanService;
import com.ezen.antpeople.service.ScheService;
import com.ezen.antpeople.service.UserService;


@Controller
@SessionAttributes("user")
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	UserService userService;
	ScheService scheService;
	MonthPlanService monthplanService;
	
	public OwnerController(UserService userService, ScheService scheService, MonthPlanService monthplanService) {
		this.userService = userService;
		this.scheService = scheService;
		this.monthplanService = monthplanService;
	}
	//----------------------- 근무 일정 페이지 ---------------------------
	
	// 운영 계획 - 1
	@RequestMapping("monthplanpage")		// nav에 있는 monthplanpage(운영계획)을 클릭함
	public ModelAndView monthplanpage(ModelAndView mav, HttpServletRequest request) {
		logger.info("monthplanpage");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		logger.info("userDto : "+userDto.toString());
		String sche = monthplanService.monthPlanList(userDto);
		logger.info("monthList Data : "+sche.toString());
		mav.addObject("monthList", sche);	// DB에 데이터가 있는 월들을 저장
		mav.setViewName("monthplanpage");	// monthplanpage로 이동
		return mav;
	}
	
	// 클릭한 월 받아서 해당 데이터 넘겨주기 - 2
	@RequestMapping(value="monthplan", method = RequestMethod.POST, produces = "application/json; charset=utf8")	// monthplanpage에 있는 monthplan(월별 리스트)를 클릭함
	@ResponseBody
	public String monthplan(@RequestBody String date, HttpServletRequest request, ScheUserListDTO schedules) throws Exception {
		logger.info("monthplan");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		schedules = scheService.findAllMonthAndUser(userDto, date);
		logger.info(schedules.toString());
		return schedules.toString();	// 클릭한 월의 데이터를 보냄
	}
	
	// 생성 버튼 3-1
	@RequestMapping("createplan")	// monthplanpage에서 createplan(생성버튼)을 클릭함
	@ResponseBody
	public String insertPlanPage(HttpServletRequest request, @RequestBody String date) throws Exception {
		logger.info("createplan");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		logger.info("userDto : "+userDto.toString());
		return "insertplanpage";	// insertplanpage로 이동
	}
	
	// insertplanpage로 이동 3-1-1
	@RequestMapping(value="insertplanpage", method=RequestMethod.GET)
	public String goinsertplanpage(Model model, String date) throws Exception {
		logger.info("insertplanpage");
		model.addAttribute("monthIndex", date);
		logger.info("date : "+ date);
		return "insertplanpage";
	}
	
	// 생성완료 버튼 3-1-2
	@RequestMapping("insertplan")		// insertplanpage에서 insertplan(완료버튼)을 클릭함
	@ResponseBody
	public String insertPlan(HttpServletRequest request, @RequestBody Map<String, ScheDetailDTO> schedules
			,String month) throws Exception {
		logger.info("insertplan");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		scheService.saveSchedules(schedules);
		monthplanService.newMonthPlan(userDto.getUser_id(), month);
		return "monthplanpage";		// monthplanpage로 이동
	}
	
	// 수정페이지 이동 버튼  3-2
	@RequestMapping("modifyplan")		// monthplanpage에서 modifyplan(수정버튼)을 클릭함
	@ResponseBody
	public String updatePlanPage() throws Exception {
	logger.info("modifyplan");
		return "updateplanpage";		// updateplanpage로 이동
	}
	
	// 수정페이지 이동 버튼 3-2-1
	@RequestMapping(value="updateplanpage", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView goUpdatePlanPage(ModelAndView mv, String date, HttpServletRequest request) throws Exception {
		logger.info("updateplanpage");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		Set<ScheDetailDTO> schedules = scheService.findAllMonth(userDto.getUser_id(), date);
		logger.info("date : "+ date);
		logger.info("list : "+ schedules);
		mv.addObject("monthIndex", date);
		mv.addObject("jsonList", schedules);
		mv.setViewName("updateplanpage");
		return mv;
	}
	
	// 수정완료 버튼 3-2-2
	@RequestMapping("updateplan")		// updateplanpage에서 updateplan(완료버튼)을 클릭함
	@ResponseBody
	public String updatePlan(@RequestBody Map<String, ScheDetailDTO> schedules) throws Exception {
		logger.info("updateplan");
		scheService.updateSchedule(schedules);
		return "monthplanpage";			// monthplanpage로 이동
	}
	
//	----------------------------- accept 페이지 ---------------------------------------------
	// 승인페이지 이동
	@RequestMapping("acceptpage")
	public ModelAndView goAccept(ModelAndView mav, HttpServletRequest request) throws Exception {
		logger.info("accept 페이지");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		String sche = monthplanService.monthPlanList(userDto);
		logger.info("userDto : "+userDto.toString());
		logger.info("monthplanpage Data : "+sche.toString());
		mav.addObject("monthIndex", sche);	// DB에 데이터가 있는 월들을 저장
		mav.setViewName("accept");
		return mav;
	}
	
	// 월별 일정 수정 / 수정 불가 변경 버튼 조작
	@RequestMapping("monthtf")
	@ResponseBody
	public String monthTF(@RequestBody Map<String,Boolean> month, HttpServletRequest request) throws Exception{
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		monthplanService.stateMonthPlan(userDto.getUser_id(), month);
		return "acceptpage";
	}
	
	// 월 클릭시 클릭한 월 받아서 해당 데이터 보여줌
	@RequestMapping(value="clickMonth", method = RequestMethod.POST, produces = "application/json; charset=utf8")	// monthplanpage에 있는 monthplan(월별 리스트)를 클릭함
	@ResponseBody
	public String clickMonth(@RequestBody String date, HttpServletRequest request, ScheUserListDTO schedules) throws Exception {
		logger.info("clickMonth");
		HttpSession httpSession = request.getSession(true);
		UserDetailDTO userDto = (UserDetailDTO) httpSession.getAttribute("user");
		schedules = scheService.findAllMonthAndUser(userDto, date);
		logger.info(schedules.toString());
		return schedules.toString();	// 클릭한 월의 데이터를 보냄
	}
	
	// 근무 승인 버튼 클릭 시
	@RequestMapping("modifymonthplan")
	@ResponseBody
	public String modifyworking(ModelAndView mav, @RequestBody Map<Integer, Set<String>> schedules
			,String month) throws Exception {
		logger.info("근무 수정");
		logger.info("스케쥴 목록 : " + schedules);
		logger.info("날짜 : " + month);
		scheService.permissionSchedule(schedules, month);
		return "acceptpage";
	}
	
}
