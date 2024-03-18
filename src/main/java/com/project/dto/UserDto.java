package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.project.entities.Questions;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private Long userId;
	private String email;
	private String password;
	private String fullname;
	private String role;
	
	private List<Questions> questions = new ArrayList<>();
	
	public UserDto(String email, String password, String fullname, String role) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public UserDto(String email, String fullname) {
		super();
		this.email = email;
		this.fullname = fullname;
	}

	public UserDto(Long userId, String email, String password, String fullname, String role) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public UserDto(Long userId, String email, String fullname) {
		super();
		this.userId = userId;
		this.email = email;
		this.fullname = fullname;
	}

	

	

}
