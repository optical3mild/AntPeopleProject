package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="user_todo")
@Getter
public class TodoRelation implements Serializable {
	
	@Id
	private int relation_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity toUser;
	
	@ManyToOne
	@JoinColumn(name="todo_id")
	private TodoEntity todo;
	

}
