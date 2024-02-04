package com.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private String email;
	private String password;
	private String fullname;
	private String role;
	
	public UserDto(String email, String password, String fullname, String role) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}


}
