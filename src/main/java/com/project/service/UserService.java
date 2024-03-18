package com.project.service;

import java.util.List;


import com.project.dto.UserDto;
import com.project.entities.User;
import com.project.entities.engineer.Aerospace;

public interface UserService {
	
	User save (UserDto userDto);

	UserDto getCurrentUser();


	  UserDto findByUsername(String username);
	  
	UserDto createUser(UserDto user);
	
	
	UserDto updateUser(UserDto user, Long userId);

	UserDto getUserById(Long userId);

	List<UserDto> getAllUsers();

	void deleteUser(Long userId);

	
	public User dtoToUser(UserDto userDto);
	
	
	

}
