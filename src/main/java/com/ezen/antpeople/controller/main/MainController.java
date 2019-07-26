package com.ezen.antpeople.controller.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ezen.antpeople.dto.board.BbsDetailDTO;
import com.ezen.antpeople.dto.board.NoticeDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.BbsService;
import com.ezen.antpeople.service.NoticeService;
import com.ezen.antpeople.service.UserService;

@Controller("main")
@SessionAttributes({ "user_id", "name", "email", "role", "store" })
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
	public String mainPage() {
		logger.info("mainpage 페이지");
		return "main";
	}

	// --------------------------------------------------------------------------

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
	public ModelAndView insertBbs(ModelAndView mv) {
		mv.addObject("isNew", "newArticle");
		mv.addObject("nextControl", 1);
		mv.setView(new RedirectView("articleallotter"));
		return mv;
	}

	// 게시글 상세 보기
	@RequestMapping("detailbbs")
	public ModelAndView detailBbs(int id, ModelAndView mv) {
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
		mv.addObject("isNew", "modifyArticle");
		mv.addObject("nextControl", 2);
		mv.setView(new RedirectView("articleallotter"));
		return mv;
	}
	// --------------------------------------------------------------------------

	// ------------------------- notice 관련 --------------------------------------
	// notice이동 및 리스트 호출
	@RequestMapping("noticepage")
	public String noticePage(Model model) {
		List<BbsDetailDTO> noticeDetailList = new ArrayList(noticeService.findByAll());
		model.addAttribute("noticeList", noticeDetailList);
		logger.info("notice 페이지");
		return "notice";
	}

	// 공지글 작성 페이지
	@RequestMapping("insertnoticepage")
	public ModelAndView insertNotice(ModelAndView mv) {
		mv.addObject("isNew", "newArticle");
		mv.addObject("nextControl", 3);
		mv.setView(new RedirectView("articleallotter"));
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
		mv.setView(new RedirectView("articleallotter"));
		return mv;
	}
	
	@RequestMapping("articleallotter")
	@ResponseBody
	public ModelAndView nextControl(ModelAndView mav, int articleNum, String title, String discription, HttpServletRequest request, int bbs_id) throws Exception {
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		mav.addObject("bbs_id", bbs_id);
		mav.addObject("user", user);
		mav.addObject("title", title);
		mav.addObject("discription", discription);
		switch (articleNum) {
		case 1 :
			mav.setViewName("insertbbs");
			break;
		case 2 :
			mav.setViewName("updatebbs");
			break;
		case 3 :
			mav.setViewName("insertnotice");
			break;
		case 4 :
			mav.setViewName("updatenotice");
			break;
		}
		return mav;
	}
	
	@RequestMapping("insertbbs")
	public String insertBbs(Model model,@RequestParam("user") UserDetailDTO user, String title, String discription) throws Exception {
		bbsService.uploadBbs(new BbsDetailDTO(title, discription, 1, user));
		return "bbspage";
	}
	
	@RequestMapping("updatebbs")
	public String updateBbs(int bbs_id, String title, String discription) throws Exception {
		bbsService.updateBbs(new BbsDetailDTO(bbs_id, title, discription, 2));
		return "bbspage";
	}
	
	@RequestMapping("insertnotice")
	public String insertNotice(Model model,@RequestParam("user") UserDetailDTO user, String title, String discription) throws Exception {
		noticeService.uploadNotice(new NoticeDetailDTO(title, discription, 1, user));
		return "noticepage";
	}
	
	@RequestMapping("updatenotice")
	public String updateNotice(int bbs_id, String title, String discription) throws Exception {
		noticeService.updateNotice(new NoticeDetailDTO(bbs_id, title, discription, 2));
		return "noticepage";
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
