package com.ezen.antpeople.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ezen.antpeople.dto.user.UserDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Getter
public  class UserEntity extends BaseEntity {
	
	@Email(message ="*Please provide a valid Email")
	@NotEmpty(message = "*Please provide your email")
	private String email;
	

	@Length(min=5, message="*Your password must have at least 5 characters")
	@NotEmpty(message="*Please provide your password")
	private String password;
	

	@NotEmpty(message="*Please provide your name")
	private String name;
	
	private String active;
	private String address;
	private String phone;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Set<RoleEntity> role;
	
	
	//TO-DO 필요시 메소드를 통해 setter 기능을 추가한다.
	
	//Entity -> DTO
	public UserDTO buildDomain() {
		UserDTO user = new UserDTO(id,email,password,name,active,address,phone,role);
		return user;
	}
	
	//user생성
	public void buildEntity(UserDTO user, Set<RoleEntity> role){
		this.email = user.getEmail();
		this.name = user.getName();
		this.active = user.getActive();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.role = role;
	}
	
	//user 정보 업데이트
	public void updateUser(UserDTO user) {
		this.address = user.getAddress();
		this.phone = user.getPhone();
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserEntity [email=" + email + ", password=" + password + ", name=" + name + ", active=" + active
				+ ", address=" + address + ", phone=" + phone + ", roles=" + role + "]";
	}
}
