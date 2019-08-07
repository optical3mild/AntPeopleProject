package com.ezen.antpeople.dto.sche;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MonthPlanListDTO {
	private List<MonthPlanDTO> planList;
	
	public MonthPlanListDTO(List<MonthPlanDTO> planList) {
		this.planList = planList;
	}
	
	@Override
	public String toString(){
		String planString = "{";
		for(MonthPlanDTO plan : planList) {
			planString += "\"" + plan.getMonth() + "\":"+plan.isState()+",";
		}
		planString = planString.substring(0, planString.length()-1) + "}";
		return planString;
	}
}
