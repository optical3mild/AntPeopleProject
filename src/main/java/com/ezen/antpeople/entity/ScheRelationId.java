package com.ezen.antpeople.entity;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ScheRelationId implements Serializable {
	
	@EqualsAndHashCode.Include
	private Integer toUser;
	@EqualsAndHashCode.Include
	private Integer sche;
	
	public ScheRelationId(Integer toUser, Integer sche) {
		this.toUser = toUser;
		this.sche = sche;
	}
	
}
