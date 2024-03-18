package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.Industrial;

public interface IndustrialService {

	Industrial createUser(Industrial user);

	List<Industrial> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	Industrial updateUser(Industrial userDto, String jobField) throws Exception;

	Industrial getUserById(String jobField) throws Exception;

}
