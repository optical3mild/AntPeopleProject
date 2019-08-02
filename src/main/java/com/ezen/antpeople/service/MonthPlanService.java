package com.ezen.antpeople.service;

import java.util.List;

import com.ezen.antpeople.dto.sche.MonthPlanDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;

public interface MonthPlanService {
	//월별 일정 확인
	public MonthPlanDTO monthPlan();
	public List<MonthPlanDTO> monthPlanList(UserDetailDTO user);
	//월별 일정 추가
	public void newMonthPlan(int user_id, String date);
	//월별 리스트 수정 가능 여부
	public void stateMonthPlan(int user_id, String date, boolean state);
}
