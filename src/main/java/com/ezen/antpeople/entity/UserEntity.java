package com.ezen.antpeople.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ezen.antpeople.dto.user.UserDTO;

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
public class UserEntity extends BaseEntity implements Serializable {
	
	@Email(message ="*Please provide a valid Email")
	@NotEmpty(message = "*Please provide your email")
	private String email;

	@Length(min=5, message="*Your password must have at least 5 characters")
	@NotEmpty(message="*Please provide your password")
	private String password;

	@NotEmpty(message="*Please provide your name")
	private String name;
	
	private int state;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private RoleEntity role;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="store_id")
	private StoreEntity store;
	
	//TO-DO 필요시 메소드를 통해 setter 기능을 추가한다.
	
	public UserDTO buildDTO( ) {
		return new UserDTO();
	}
	
	public void buildEntity(UserDTO user) {
		
	}
	
	
	
}
