package com.ezen.antpeople.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

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
	
	private boolean state;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(	name="user_todo", 
				joinColumns = @JoinColumn(name="todo_id",referencedColumnName = "todo_id"),
				inverseJoinColumns = @JoinColumn(name="to_id", referencedColumnName = "user_id"))
	private Set<UserEntity> toUser;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(	name="user_todo", 
				joinColumns = @JoinColumn(name="todo_id",referencedColumnName = "todo_id"),
				inverseJoinColumns = @JoinColumn(name="dear_id", referencedColumnName = "user_id"))
	private Set<UserEntity> dearUser;

}
