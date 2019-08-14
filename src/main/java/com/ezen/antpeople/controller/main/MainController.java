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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.antpeople.dto.board.BbsDetailDTO;
import com.ezen.antpeople.dto.board.NoticeDetailDTO;
import com.ezen.antpeople.dto.sche.ScheUserDTO;
import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.user.RoleDTO;
import com.ezen.antpeople.dto.user.StoreDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.service.BbsService;
import com.ezen.antpeople.service.MonthPlanService;
import com.ezen.antpeople.service.NoticeService;
import com.ezen.antpeople.service.ScheService;
import com.ezen.antpeople.service.TodoService;
import com.ezen.antpeople.service.UserService;

@Controller
@SessionAttributes("user")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	UserService userService;
	BbsService bbsService;
	NoticeService noticeService;
	TodoService todoService;
	ScheService scheService;
	MonthPlanService monthplanService;

	public MainController(UserService userService, BbsService bbsService, NoticeService noticeService, TodoService todoService, ScheService scheService, MonthPlanService monthplanService) {
		this.userService = userService;
		this.bbsService = bbsService;
		this.noticeService = noticeService;
		this.todoService = todoService;
		this.scheService = scheService;
		this.monthplanService = monthplanService;
	}

	// 메인 페이지
	@RequestMapping("mainpage")
	public ModelAndView mainPage(ModelAndView mv,HttpServletRequest request) {
		logger.info("mainpage 페이지 시작");
		HttpSession session = request.getSession(true);
		Optional<UserDetailDTO> user = Optional.ofNullable((UserDetailDTO) session.getAttribute("user")); //세션 존재 여부 확인
		logger.info("세션 받아옴");
		int date = Integer.parseInt(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyMMdd")));
		int staffApply =0;
		int staffRefuseApply =0;
		String todoList = "";
		List<NoticeDetailDTO> noticeDetailList = new ArrayList<NoticeDetailDTO>(noticeService.findTopFive()); //5개의 공지사항 게시물
		List<BbsDetailDTO> bbsDetailList = new ArrayList<BbsDetailDTO>(bbsService.findTopFive()); //5개의 자유게시판 게시물
		List<ScheUserDTO> todayStaffList = new ArrayList<ScheUserDTO>(); //오늘 근무하는 사람
		UserDetailDTO userDto = (UserDetailDTO) session.getAttribute("user");
		String sche = new String();
		List <ScheUserDTO> own = new ArrayList<ScheUserDTO>();
		int count = 0;
		int counttodo = 0;
		int countApply = 0;
		if(user.isPresent()) {
			todayStaffList = userService.todayStaff(user.get().getStore().getStore(), Integer.toString(date));
			staffApply = userService.applyScheduleCount(user.get().getUser_id(), 1); //일정 신청 대기중인 목록 수
			staffRefuseApply = userService.applyScheduleCount(user.get().getUser_id(), 3); // 일정 신청이 거절된 목록 수
			todoList = todoService.TodoListAll(user.get());
			sche = monthplanService.monthPlanList(userDto);
			own = scheService.scheUserList(userDto.getStore().getStore());
			count = StringUtils.countOccurrencesOf(sche, "true");
			counttodo = StringUtils.countOccurrencesOf(todoList, "false");
		}
		logger.info("countApply : " + countApply);
		logger.info("count : " + count);
		logger.info("todoList : " +todoList);
		mv.addObject("staffRefuseApply", staffRefuseApply);
		mv.addObject("staffApply", staffApply);
		mv.addObject("countApply", own);
		mv.addObject("bbsList", bbsDetailList);
		mv.addObject("noticeList", noticeDetailList);
		mv.addObject("todayStaffList", todayStaffList);
		mv.addObject("todoList", todoList);
		mv.addObject("notyetplan", count);
		mv.addObject("counttodo", counttodo);
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

	// --------------------------------- todo -----------------------------------------

	// todo 새 글 작성 
	@RequestMapping(value = "popupToDoBoard", produces = "application/json; charset=utf8")
	@ResponseBody
	public String popupToDoBoard(HttpServletRequest request) throws Exception {
		logger.info("todo 새 글 작성");
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		String todoUserList = userService.todoUserStringList(user.getStore().getStore());
		logger.info("todoUserList : "+todoUserList.toString());
		return todoUserList;
	}
	
	// todo 작성 완료 
	@RequestMapping(value="makeToDoItem",produces = "application/json; charset=utf8")
	@ResponseBody
	public String makeToDoItem(Model model, @RequestBody TodoDetailDTO todo, HttpServletRequest request) throws Exception {
		logger.info("makeToDoItem");
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		todo.fromUser(user.buildDTOTodo());
		logger.info("todo 상세 : " + todo.getFromUser().toString());
		todoService.uploadTodo(todo);
		String todoList = todoService.TodoListAll(user);
		logger.info("todoList : "+todoList);
		return todoList;
	}

	// todo send 삭제 
	@RequestMapping(value ="senditemdelete",produces = "application/json; charset=utf8")
	@ResponseBody
	public String sendItemDelete(HttpServletRequest request, @RequestBody TodoDetailDTO todo) throws Exception {
		logger.info("senditemdelete");
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		logger.info(todo.toString());
		todoService.deleteTodo(todo.getId());
		return todoService.TodoListAll(user);
	}
	
	// todo recive 삭제
	@RequestMapping(value ="receiveditemdelete",produces = "application/json; charset=utf8")
	@ResponseBody
	public String recivedItemDelete(HttpServletRequest request, @RequestBody TodoDetailDTO todo) throws Exception {
		logger.info("receiveditemdelete");
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		todoService.deleteSendTodo(todo.getId(), user.getUser_id());
		return todoService.TodoListAll(user);
	}
	
	// todo recive check 
	@RequestMapping(value="receiveditemcheck",produces="application/json; charset=utf8")
	@ResponseBody
	public String recivedItemCheck(HttpServletRequest request, @RequestBody TodoDetailDTO todo) throws Exception {
		logger.info("receiveditemcheck");
		HttpSession session = request.getSession();
		UserDetailDTO user = (UserDetailDTO) session.getAttribute("user");
		todoService.checkTodo(todo.getId(), user.getUser_id());
		return todoService.TodoListAll(user);
	}
	
}