package com.ezen.antpeople.entity;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MonthPlanEntityId implements Serializable {

	@EqualsAndHashCode.Include
	private Integer user;
	@EqualsAndHashCode.Include
	private String month;
	
	public MonthPlanEntityId(Integer user, String month) {
		this.user = user;
		this.month = month;
	}
}
