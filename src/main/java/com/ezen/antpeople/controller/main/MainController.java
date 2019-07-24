package com.ezen.antpeople.controller.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.antpeople.dto.bbs.BbsDetailDTO;
import com.ezen.antpeople.service.BbsService;
import com.ezen.antpeople.service.UserService;


@Controller("main")
@SessionAttributes({"user_id","name","email","role","store"})
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	UserService userService;
	BbsService bbsService;
	
	public MainController(UserService userService, BbsService bbsService) {
		this.userService = userService;
		this.bbsService = bbsService;
	}
	
	// 게스트 메인 페이지
	@RequestMapping("guestmain")
	public String guestMain() {
		logger.info("guestMain 페이지");
		return "guestmain";
	}
	
	// 메인 페이지
		@RequestMapping("mainpage")
		public String mainPage() {
			logger.info("guestMain 페이지");
			return "main";
		}
	
	// 공지
	@RequestMapping("notice")
	public String notice() {
		logger.info("notice 페이지");
		return "notice";
	}
	
	@RequestMapping("bbs")
	public String bbsPage(Model model) {
		List<BbsDetailDTO> bbsDetailList = new ArrayList(bbsService.findByAll());
		model.addAttribute("bbsList",bbsDetailList);
		logger.info("bbs 페이지");
		return "bbs";
	}
	
	// 직원 전체목록(간략)
	@RequestMapping("/stafflist")
	public String stafflist() throws Exception {
		logger.info("staffList 페이지");
		return "pages/stafflist";
	}
	
	/*
	 * @RequestMapping("/pages/privatecalender") public ModelAndView
	 * privateCalender(ModelAndView mav, @RequestParam("user_id") int user_id)
	 * throws Exception { UserDTO user = userService.getUser(user_id);
	 * mav.setViewName("pages/calender"); mav.addObject("list", user); return mav; }
	 */
	// 개인 별 일정(달력) 데이터 보내기
	/*
	 * @RequestMapping("privatecalender") public ModelAndView
	 * privateCalender(ModelAndView mav, int user_id) throws Exception { UserDTO
	 * user = userService.getUser(user_id); mav.setViewName("pages/calender");
	 * mav.addObject("list", user); return mav; }
	 * 
	 * // 금일 근무자 목록
	 * 
	 * @RequestMapping("todaystaff") public String todaystaff(Model model) throws
	 * Exception {
	 * 
	 * return "todaystaff"; }
	 */
	
}
