package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.Chemical;

public interface ChemicalService {

	Chemical createUser(Chemical user);

	List<Chemical> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	Chemical updateUser(Chemical userDto, String jobField) throws Exception;

	Chemical getUserById(String jobField) throws Exception;

}
