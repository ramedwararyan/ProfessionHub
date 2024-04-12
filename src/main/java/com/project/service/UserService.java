package com.project.service;

import java.util.List;


import com.project.dto.UserDto;
import com.project.entities.User;


public interface UserService {
	
	User save (UserDto userDto);

	UserDto getCurrentUser();


	  UserDto findByUsername(String username);
	  
	UserDto createUser(UserDto user);
	
	
	 UserDto getUserById(Long userId);
	    void updateUser(Long userId, UserDto userDto);
	
	public User dtoToUser(UserDto userDto);
	
	public UserDto userToDto(User user);
	

}
