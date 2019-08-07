package com.ezen.antpeople.service;

import java.util.List;

import com.ezen.antpeople.dto.board.NoticeDetailDTO;

public interface NoticeService {
	public void uploadNotice(NoticeDetailDTO notice); //공지사항 작성
	public void updateNotice(NoticeDetailDTO notice); //공지사항 수정 
	public void deleteNotice(int bbs_id); //공지사항 삭제
	public NoticeDetailDTO findByOne(Integer notice_id); // 공지사항 상세 보기
	public List<NoticeDetailDTO> findByAll(); // 공지사항 리스트
	public List<NoticeDetailDTO> findTopFive(); // 메인 - 공지사항 리스트 (5개만)
}
