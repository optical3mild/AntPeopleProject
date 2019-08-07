package com.ezen.antpeople.controller.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.board.BbsDetailDTO;
import com.ezen.antpeople.dto.board.NoticeDetailDTO;
import com.ezen.antpeople.dto.user.RoleDTO;
import com.ezen.antpeople.dto.user.StoreDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.repository.USRepository;
import com.ezen.antpeople.service.BbsService;
import com.ezen.antpeople.service.NoticeService;
import com.ezen.antpeople.service.UserService;

@Controller
@SessionAttributes("user")
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

	// 메인 페이지
	@RequestMapping("mainpage")
	public ModelAndView mainPage(ModelAndView mv,HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Optional<UserDetailDTO> user = Optional.ofNullable((UserDetailDTO) session.getAttribute("user")); //세션 존재 여부 확인
		int date = Integer.parseInt(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyMMdd")));
		int staffApply =0;
		int staffRefuseApply =0;
		List<BbsDetailDTO> bbsDetailList = new ArrayList<BbsDetailDTO>(bbsService.findByAll());
		List<NoticeDetailDTO> noticeDetailList = new ArrayList<NoticeDetailDTO>(noticeService.findByAll());
		List<UserDetailDTO> todayStaffList = new ArrayList<UserDetailDTO>();
		if(user.isPresent()) {
			todayStaffList = userService.todayStaff(user.get().getStore().getStore(), Integer.toString(date));
			staffApply = userService.applyScheduleCount(user.get().getUser_id(), 1); //일정 신청 대기중인 목록 수
			staffRefuseApply = userService.applyScheduleCount(user.get().getUser_id(), 3); // 일정 신청이 거절된 목록 수
		}
		mv.addObject("staffRefuseApply", staffRefuseApply);
		mv.addObject("staffApply", staffApply);
		mv.addObject("bbsList", bbsDetailList);
		mv.addObject("noticeList", noticeDetailList);
		mv.addObject("todayStaffList", todayStaffList);
		mv.setViewName("main");
		logger.info("mainpage 페이지");
		return mv;
	}

	// --------------------------------------------------------------------------
	// ------------------------------ 직원 목록 관련 ----------------------------
	// 직원 전체 정보 목록
		@RequestMapping("staffinfo")
		public ModelAndView staffInfo(ModelAndView mv,HttpServletRequest request) throws Exception {
			logger.info("staffInfo 페이지");
			//세션 받아오기
			HttpSession session = request.getSession();
			UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
			StoreDTO store = user.getStore();
			System.out.println(store.toString());
			List<UserDetailDTO> userList = new ArrayList<UserDetailDTO>(userService.findByStore(new RoleDTO(102,""),store));
			mv.addObject("userList", userList);
			mv.setViewName("staffinfo");
			return mv;
		}
	
	// ----------------------------- bbs 관련 -------------------------------------
	// bbs이동 및 리스트 호출
	@RequestMapping("bbspage")
	public String bbsPage(Model model) {
		List<BbsDetailDTO> bbsDetailList = new ArrayList(bbsService.findByAll());
		model.addAttribute("bbsList", bbsDetailList);
		logger.info("bbs 페이지");
		return "bbs";
	}

	// 게시글 작성 페이지
	@RequestMapping("insertbbspage")
	public ModelAndView insertBbsPage(ModelAndView mv) {
		logger.info("insertbbspage");
		mv.addObject("isNew", "newArticle");
		mv.addObject("nextControl", 1);
		mv.setViewName("writearticle");
		return mv;
	}

	// 게시글 상세 보기
	@RequestMapping("detailbbs")
	public ModelAndView detailBbs(int id, ModelAndView mv) {
		logger.info("detailbbs");
		mv.addObject("detail", bbsService.findByOne(id));
		mv.addObject("category", "자유게시판");
		mv.setViewName("articledetail");
		return mv;
	}

	// 게시글 삭제하기
	@RequestMapping("deletebbs")
	public ModelAndView deleteBbs(int id, ModelAndView mv) {
		bbsService.deleteBbs(id);
		mv.setViewName("redirect:bbspage");
		return mv;
	}

	// 게시글 수정하기
	@RequestMapping("updatebbspage")
	public ModelAndView updateBbs(int id, ModelAndView mv) {
		mv.addObject("bbsDetail", bbsService.findByOne(id));
		mv.addObject("isNew", "modifyBbs");
		mv.addObject("nextControl", 2);
		mv.setViewName("writearticle");
		return mv;
	}
	// --------------------------------------------------------------------------

	// ------------------------- notice 관련 --------------------------------------
	// notice이동 및 리스트 호출
	@RequestMapping("noticepage")
	public String noticePage(Model model) throws Exception {
		List<BbsDetailDTO> noticeDetailList = new ArrayList(noticeService.findByAll());
		model.addAttribute("noticeList", noticeDetailList);
		logger.info("notice 페이지");
		return "notice";
	}

	// 공지글 작성 페이지
	@RequestMapping("insertnoticepage")
	public ModelAndView insertNotice(ModelAndView mv) {
		logger.info("insertnoticepage");
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
		logger.info("MainController - updatenoticepage");
		logger.info("사용자 id : " + id);
		mv.addObject("noticeDetail", noticeService.findByOne(id));
		mv.addObject("isNew", "modifyNotice");
		mv.addObject("nextControl", 4);
		mv.setViewName("writearticle");
		return mv;
	}
	
	@RequestMapping("articleallotter")
	@ResponseBody
	public ModelAndView nextControl(ModelAndView model, int articleNum, String title, String description, HttpServletRequest request, int bbs_id) {
		logger.info("articleallotter");
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		logger.info("내용 : "+ description);
		logger.info(user.toString());
		model.addObject("bbs_id", bbs_id);
		model.addObject("title", title);
		model.addObject("description", description);
		model.addObject("user", user);
		logger.info("articleallotter.mav : "+model.toString());
		String a = "" + articleNum;
		logger.info(a.toString());
		switch (articleNum) {
		case 1 :
			logger.info("articleNum = 1");
			bbsService.uploadBbs(new BbsDetailDTO(title, description, 1, user));
			model.setViewName("redirect:bbspage");
			break;
		case 2 :
			logger.info("articleNum = 2");
			bbsService.updateBbs(new BbsDetailDTO(bbs_id, title, description, 2));
			model.setViewName("redirect:bbspage");
			break;
		case 3 :
			logger.info("articleNum = 3");
			noticeService.uploadNotice(new NoticeDetailDTO(title, description, 1, user));
			model.setViewName("redirect:noticepage");
			break;
		case 4 :
			logger.info("articleNum = 4");
			noticeService.updateNotice(new NoticeDetailDTO(bbs_id, title, description, 2));
			model.setViewName("redirect:noticepage");
			break;
		}
		return model;
	}
	

	// --------------------------------------------------------------------------

	// --------------------------------------------------------------------------

	// 직원 전체목록(간략)
	@RequestMapping("/stafflist")
	public String stafflist() throws Exception {
		logger.info("staffList 페이지");
		return "stafflist";
	}

	@RequestMapping("todaystaff")
	public String todaystaff() throws Exception {
		return "todaystaff";
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
	 */

}
