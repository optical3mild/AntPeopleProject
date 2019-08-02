package com.ezen.antpeople.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.sche.MonthPlanDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="month_plan")
@Getter
@IdClass(MonthPlanEntityId.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MonthPlanEntity {

	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@Id
	private String month;
	
	private boolean state;
	
	public MonthPlanEntity(MonthPlanDTO monthPlan) {
		this.user = new UserEntity(new UserDetailDTO(monthPlan.getUser_id(),""));
		this.month = monthPlan.getMonth();
		this.state = monthPlan.isState();	
	}
	
	public MonthPlanDTO buildDTO() {
		return new MonthPlanDTO(user.getId(), month, state);
	}
	
	public void updateState(boolean state) {
		this.state = state;
	}
	
}
