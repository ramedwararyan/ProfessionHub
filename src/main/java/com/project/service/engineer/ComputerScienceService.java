package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.ComputerScience;

public interface ComputerScienceService {

	ComputerScience createUser(ComputerScience user);

	List<ComputerScience> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	ComputerScience updateUser(ComputerScience userDto, String jobField) throws Exception;

	ComputerScience getUserById(String jobField) throws Exception;

}
