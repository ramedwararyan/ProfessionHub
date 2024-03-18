package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.Electrical;

public interface ElectricalService {

	Electrical createUser(Electrical user);

	List<Electrical> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	Electrical updateUser(Electrical userDto, String jobField) throws Exception;

	Electrical getUserById(String jobField) throws Exception;

}
