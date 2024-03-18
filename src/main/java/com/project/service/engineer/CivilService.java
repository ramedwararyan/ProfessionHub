package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.Civil;

public interface CivilService {

	Civil createUser(Civil user);

	List<Civil> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	Civil updateUser(Civil userDto, String jobField) throws Exception;

	Civil getUserById(String jobField) throws Exception;

}
