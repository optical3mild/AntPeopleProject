package com.ezen.antpeople.entity;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoRelationId implements Serializable{
	
	@EqualsAndHashCode.Include
	private Integer toUser;
	@EqualsAndHashCode.Include
	private Integer todo;
	
	public TodoRelationId(Integer toUser, Integer todo) {
		this.toUser = toUser;
		this.todo = todo;
	}

}
