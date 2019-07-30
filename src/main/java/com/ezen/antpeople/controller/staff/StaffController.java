package com.ezen.antpeople.controller.staff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.antpeople.dto.sche.ScheDetailDTO;

@Controller
public class StaffController {
	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);
	
//	 StaffService staffService;
//  
//	 public StaffController (StaffService staffService) { 
//		 this.staffService = staffService; 
//	 }
	
	//근무신청 페이지로 이동
	@RequestMapping("requestwork")
	public String goRequestwork() throws Exception {
		logger.info("requestWork");
		return "requestwork";
	}

	// 근무 신청
	@RequestMapping("requestworking")
	@ResponseBody
	public String requestworking(Model model, ScheDetailDTO sche) throws Exception {
		logger.info("근무신청");
//		ScheService.#;						// 추가 필요
		return "redirect:../main/main";
	}
	
	// 근무 수정 페이지로
	@RequestMapping("modifywork")
	public String modifywork() throws Exception {
		return "modifywork";
	}
	
	// 근무 수정
	@RequestMapping("modifyworking")
	public String modifyworking(Model model, ScheDetailDTO sche) throws Exception {
		logger.info("근무 수정");
//		ScheService.#;						// 추가 필요
		return "redirect:../main/main";
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
