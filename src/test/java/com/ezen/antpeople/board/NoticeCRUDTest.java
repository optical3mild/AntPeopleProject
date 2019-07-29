package com.ezen.antpeople.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ezen.antpeople.config.RootConfig;
import com.ezen.antpeople.config.SecurityConfig;
import com.ezen.antpeople.dto.board.NoticeDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.repository.NoticeRepository;
import com.ezen.antpeople.service.NoticeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //서버에서 생성되지만, 테스트에서 생성되지 않는 ServletContext생성 용도
@ContextConfiguration(classes= {RootConfig.class,SecurityConfig.class})
public class NoticeCRUDTest {
	
	@Resource
	NoticeService ns;
	
	@Resource
	NoticeRepository nr;
	
	//공지사항 업로드 테스트 - 완료
	@Test
	public void noticeUploadTest() {
		NoticeDetailDTO notice = new NoticeDetailDTO("테스트 수정", "수정된 게시물 입니다.",1, new UserDetailDTO(3,""));
		System.out.println(notice.toString());
		ns.uploadNotice(notice);
	}
	
	//게시물 업데이트 테스트 - 완료
	@Test @Ignore
	public void noticeUpdateTest() {
		NoticeDetailDTO notice = new NoticeDetailDTO(2,"테스트 수정", "수정된 게시물 입니다.",1);
		ns.updateNotice(notice);
		
	}
	
	//게시물 상세보기 테스트 - 완료
	@Test @Ignore
	public void noticeDetailTest() {
		NoticeDetailDTO notice = ns.findByOne(4);
		System.out.println(notice.toString());
		assertEquals(notice.getTitle(), "공지사항 테스트");
	}
	
	//게시물 삭제 테스트 - 완료
	@Test @Ignore
	public void noticeDeleteTest() {
		ns.deleteNotice(1);
		assertFalse(nr.findById(1).isPresent());
	}
	
	//게시물 리스트 테스트 
	@Test @Ignore
	public void noticeListTest() {
		List<NoticeDetailDTO> noticeList = new ArrayList(ns.findByAll());
		for(NoticeDetailDTO notice : noticeList) {
			System.out.println(notice.toString());
		}
		
	}

}
