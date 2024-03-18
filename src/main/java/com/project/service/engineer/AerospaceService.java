package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.Aerospace;

public interface AerospaceService {

	Aerospace createUser(Aerospace user);

	List<Aerospace> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	Aerospace updateUser(Aerospace userDto, String jobField) throws Exception;

	Aerospace getUserById(String jobField) throws Exception;

}
