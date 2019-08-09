package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.todo.TodoUserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_todo")
@IdClass(TodoRelationId.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class TodoRelation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity toUser;
	
	@Id
	@ManyToOne
	@JoinColumn(name="todo_id")
	private TodoEntity todo;
	
	private boolean state;
	
	public TodoUserDTO buildDTO() {
		return new TodoUserDTO(this.toUser.buildDTO(),this.todo.buildDTO(), this.state);
	}
	
	public void checkTodo() {
		this.state = true;
	}
	
	
	

}
