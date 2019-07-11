package com.ezen.antpeople.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ezen.antpeople.dto.UserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class UserEntity extends BaseEntity {
	
	@Column(name="email")
	@Email(message ="*Please provide a valid Email")
	@NotEmpty(message = "*Please provide your email")
	private String email;
	
	@Column(name="password")
	@Length(min=5, message="*Your password must have at least 5 characters")
	@NotEmpty(message="*Please provide your password")
	private String password;
	
	@Column(name="name")
	@NotEmpty(message="*Please provide your name")
	private String name;
	
	@Column(name="active")
	private String active;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id")
		,inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<RoleEntity> roles;
	
	
	//TO-DO 필요시 메소드를 통해 setter 기능을 추가한다.
	
	//user생성
	public void buildEntity(UserDTO user, Set<RoleEntity> roles){
		this.email = user.getEmail();
		this.name = user.getName();
		this.active = user.getActive();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.roles = roles;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
