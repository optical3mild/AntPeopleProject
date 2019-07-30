package com.ezen.antpeople.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.ezen.antpeople.dto.todo.TodoDetailDTO;
import com.ezen.antpeople.dto.user.UserDetailDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="todo")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AttributeOverride(name="id", column=@Column(name="todo_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "created_time"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_time"))

// todoDB - todo_id, description, created_time, updated_time 
public class TodoEntity extends BaseEntity implements Serializable {
	
	@NotEmpty(message = "*내용을 적어 주세요.")
	private String description;
	
	private int state;
	
	@ManyToMany
	@JoinTable(name="user_todo", joinColumns = @JoinColumn(name="todo_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> toUsers;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity fromUser;
	
	//할일 DB저장
	public TodoEntity(TodoDetailDTO todo) {
		List<UserEntity> userList = new ArrayList();
		for(UserDetailDTO user : todo.getToUsers()) {
			userList.add(new UserEntity(user));
			System.out.println(user.toString());
		}
		this.description = todo.getDescription();
		this.fromUser = new UserEntity(todo.getFromUser());
		this.toUsers = userList;
		this.state = 1;
	}
	
	public TodoDetailDTO buildDTO() {
		List<UserDetailDTO> toUsers = new ArrayList();
		for(UserEntity user : this.toUsers)
			toUsers.add(user.buildDTO());
		return new TodoDetailDTO(this.id, this.description, this.state, this.updatedAt, this.fromUser.buildDTO(),toUsers);
	}

}
