package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ezen.antpeople.dto.user.UserDetailDTO;
import com.ezen.antpeople.dto.user.UserTodoDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@AttributeOverride(name = "createdAt", column = @Column(name = "created_time"))
@AttributeOverride(name = "updatedAt", column = @Column(name = "updated_time"))
//userDB - 	user_id, email, password, name, 
//			created_time, updated_time, role_id, store_id
public class UserEntity extends BaseEntity implements Serializable{
	
	private String email;
	private String password;
	private String name;
	private Integer state;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private RoleEntity role;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private StoreEntity store;
	
	//TO-DO 필요시 메소드를 통해 setter 기능을 추가한다.
	
	//회원가입시 유저 정보 저장
	public UserEntity(UserDetailDTO user, String password) {
		this.email = user.getEmail();
		this.password = password;
		this.name = user.getName();
		this.state = 0;
		this.role = new RoleEntity(user.getRole());
		this.store = new StoreEntity(user.getStore());
	}
	
	//게시판 글 작성시
	public UserEntity(UserDetailDTO user) {
		this.id = user.getUser_id();
		this.name = user.getName();
	}
	
	//할일 리스트 작성 시 
	public UserEntity(UserTodoDTO user) {
		this.id = user.getUserId();
		this.email = user.getEmail();
		this.name = user.getUserName();
	}
	
	//로그인시 유저 상세 정보
	public UserDetailDTO buildDTO() {
		return new UserDetailDTO(this.id, this.email, this.password, this.name,
				this.state, this.createdAt,this.updatedAt, this.role.buildDTO(), this.store.buildDTO() );
	}
	//유저 간략 정보 
	public UserDetailDTO buildDTOSmall() {
		return new UserDetailDTO(this.id, this.email, this.name, this.role.buildDTO(), this.store.buildDTO() );
	}
	public UserTodoDTO buildTodoDTO() {
		return new UserTodoDTO(this.id, this.email, this.name, this.role.getRole_id());
	}

	@Override
	public String toString() {
		return "UserEntity [email=" + email + ", password=" + password + ", name=" + name + ", state=" + state
				+ ", role=" + role + ", store=" + store + "]";
	}
	
}
