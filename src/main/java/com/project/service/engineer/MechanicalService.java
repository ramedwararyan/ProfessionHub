package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.Mechanical;

public interface MechanicalService {

	Mechanical createUser(Mechanical user);

	List<Mechanical> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	Mechanical updateUser(Mechanical userDto, String jobField) throws Exception;

	Mechanical getUserById(String jobField) throws Exception;

}
