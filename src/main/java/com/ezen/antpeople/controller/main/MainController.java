package com.ezen.antpeople.controller.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.board.BbsDetailDTO;
import com.ezen.antpeople.service.BbsService;
import com.ezen.antpeople.service.NoticeService;
import com.ezen.antpeople.service.UserService;


@Controller("main")
@SessionAttributes({"user_id","name","email","role","store"})
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	UserService userService;
	BbsService bbsService;
	NoticeService noticeService;
	
	public MainController(UserService userService, BbsService bbsService, NoticeService noticeService) {
		this.userService = userService;
		this.bbsService = bbsService;
		this.noticeService = noticeService;
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

	//--------------------------------------------------------------------------
	
	
	//----------------------------- bbs 관련 -------------------------------------
	// bbs이동 및 리스트 호출
	@RequestMapping("bbspage")
	public String bbsPage(Model model) {
		List<BbsDetailDTO> bbsDetailList = new ArrayList(bbsService.findByAll());
		model.addAttribute("bbsList",bbsDetailList);
		logger.info("bbs 페이지");
		return "bbs";
	}
	
	//게시글 작성 페이지
	@RequestMapping("insertbbspage")
	public ModelAndView insertBbs(ModelAndView mv) {
		mv.addObject("isNew", "newArticle");
		mv.addObject("nextControl", 1);
		mv.setViewName("writearticle");
		return mv;
	}
	
	//게시글 상세 보기
	@RequestMapping("detailbbs")
	public ModelAndView detailBbs(int id, ModelAndView mv) {
		mv.addObject("detail", bbsService.findByOne(id));
		mv.addObject("category", "자유게시판");
		mv.setViewName("articledetail");
		return mv;
	}
	
	//게시글 삭제하기 
	@RequestMapping("deletebbs")
	public ModelAndView deleteBbs(int id, ModelAndView mv) {
		bbsService.deleteBbs(id);
		mv.setViewName("redirect:bbspage");
		return mv;
	}
	
	//게시글 수정하기
	@RequestMapping("updatebbspage")
	public ModelAndView updateBbs(int id, ModelAndView mv) {
		mv.addObject("bbsDetail", bbsService.findByOne(id));
		mv.addObject("isNew", "modifyArticle");
		mv.addObject("nextControl", 2);
		mv.setViewName("writearticle");
		return mv;
	}
	//--------------------------------------------------------------------------
	
	//------------------------- notice 관련 --------------------------------------
	// notice이동 및 리스트 호출
		@RequestMapping("noticepage")
		public String noticePage(Model model) {
			List<BbsDetailDTO> noticeDetailList = new ArrayList(noticeService.findByAll());
			model.addAttribute("noticeList",noticeDetailList);
			logger.info("notice 페이지");
			return "notice";
		}
	
	// 공지글 작성 페이지
	@RequestMapping("insertnoticepage")
	public ModelAndView insertNotice(ModelAndView mv) {
		mv.addObject("isNew", "newArticle");
		mv.addObject("nextControl", 3);
		mv.setViewName("writearticle");
		return mv;
	}
	
	// 공지글 상세 보기
	@RequestMapping("detailnotice")
	public ModelAndView detailnotice(int id, ModelAndView mv) {
		mv.addObject("detail", noticeService.findByOne(id));
		mv.addObject("category", "공지사항");
		mv.setViewName("articledetail");
		return mv;
	}
	
	// 공지글 삭제하기 
	@RequestMapping("deletenotice")
	public ModelAndView deleteNotice(int id, ModelAndView mv) {
		noticeService.deleteNotice(id);
		mv.setViewName("redirect:noticepage");
		return mv;
	}
	
	// 공지글 수정하기
	@RequestMapping("updatenoticepage")
	public ModelAndView updateNotice(int id, ModelAndView mv) {
		mv.addObject("noticeDetail", noticeService.findByOne(id));
		mv.addObject("isNew", "modifyArticle");
		mv.addObject("nextControl", 4);
		mv.setViewName("writearticle");
		return mv;
	}
	
	
	//--------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------
	
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
