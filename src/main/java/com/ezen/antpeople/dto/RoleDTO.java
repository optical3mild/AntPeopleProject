package com.ezen.antpeople.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


public class RoleDTO implements Serializable{
	
	protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
	private String role;

}
