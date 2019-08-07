package com.ezen.antpeople.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezen.antpeople.dto.board.NoticeDetailDTO;
import com.ezen.antpeople.entity.NoticeEntity;
import com.ezen.antpeople.repository.NoticeRepository;
import com.ezen.antpeople.service.NoticeService;

@Service("notice")
public class NoticeServiceImpl implements NoticeService {
	
	NoticeRepository noticeRepository;
	
	public NoticeServiceImpl(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
		
	}

	//게시글 업로드
	@Override
	public void uploadNotice(NoticeDetailDTO Notice) {
		NoticeEntity entity = new NoticeEntity(Notice);
		noticeRepository.save(entity);
	}

	//게시글 삭제
	@Override
	public void deleteNotice(int notice_id) {
		Optional<NoticeEntity> entity = noticeRepository.findById(notice_id);
		noticeRepository.delete(entity.get());
	}
	
	//게시글 수정
	@Override
	public void updateNotice(NoticeDetailDTO Notice) {
		Optional<NoticeEntity> entity = noticeRepository.findById(Notice.getNotice_id());
		entity.get().updateEntity(Notice, LocalDateTime.now());
		noticeRepository.save(entity.get());
		
	}
	
	//게시글 상세 보기
	@Override
	public NoticeDetailDTO findByOne(Integer notice_id) {
		Optional<NoticeEntity> entity = noticeRepository.findById(notice_id);
		if(entity.isPresent())
			return entity.get().buildDto();
		else 
			return null;
	}
	
	//게시글 전체 보기
	@Override
	public List<NoticeDetailDTO> findByAll() {
		List<NoticeDetailDTO> NoticeDetailList = new ArrayList<NoticeDetailDTO>();
		List<NoticeEntity> NoticeList = new ArrayList<NoticeEntity>(noticeRepository.findAllByOrderByIdDesc());
		for(NoticeEntity Notice : NoticeList)
			NoticeDetailList.add(Notice.buildDto());
		return NoticeDetailList;
	}
	
	//메인 - 공지사항 5개만 보기
	@Override
	public List<NoticeDetailDTO> findTopFive() {
		List<NoticeDetailDTO> NoticeDetailList = new ArrayList<NoticeDetailDTO>();
		List<NoticeEntity> NoticeList = new ArrayList<NoticeEntity>(noticeRepository.findTop5ByOrderByIdDesc());
		for(NoticeEntity Notice : NoticeList)
			NoticeDetailList.add(Notice.buildDto());
		return NoticeDetailList;
	}

}
