package com.ezen.antpeople.dto.board;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.Getter;

@Getter
public class NoticeDetailDTO {
	
	private int notice_id;
	
	@NotEmpty(message = "제목은 필수 입니다.")
	private String title;
	
	private String description;
	private int state;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	private UserDetailDTO user;
	
	public NoticeDetailDTO() {}
	
	//화면으로 부터 새로운 게시글을 받아옴 (게시물 업로드)
	public NoticeDetailDTO(String title, String description,int state, int user_id, String name) {
		this.title = title;
		this.description = description;
		this.state = state;
		this.user = new UserDetailDTO(user_id, name);
	}

	//화면에 출력
	public NoticeDetailDTO(int notice_id, String title, String description, int state, LocalDateTime createdAt, LocalDateTime updatedAt,
			UserDetailDTO user) {
		super();
		this.notice_id = notice_id;
		this.title = title;
		this.description = description;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}
	
	//게시물 수정
	public NoticeDetailDTO(int notice_id, String title, String description, int state) {
		super();
		this.notice_id = notice_id;
		this.title = title;
		this.description = description;
		this.state = state;
	}

	@Override
	public String toString() {
		return "BbsDetailDTO [notice_id = " + notice_id +", title=" + title + ", description=" + description + ", state=" + state + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", user=" + user.getUser_id() + "]";
	}
	
	
	
}
