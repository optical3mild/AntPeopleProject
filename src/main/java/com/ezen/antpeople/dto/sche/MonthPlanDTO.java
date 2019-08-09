package com.ezen.antpeople.dto.sche;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MonthPlanDTO {
	private Integer user_id;
	private String month;
	private boolean state;
	
	public MonthPlanDTO(Integer user_id, String month) {
		this.user_id = user_id;
		this.month = month;
	}
	
	public MonthPlanDTO(Integer user_id, String month, boolean state) {
		this.user_id = user_id;
		this.month = month;
		this.state = state;
	}

	@Override
	public String toString() {
		return "\"" + month + "\":" + state;
	}

}
