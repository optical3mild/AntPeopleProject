package com.ezen.antpeople.dto.sche;

import java.util.List;

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
