package com.project.service.engineer;

import java.util.List;

import com.project.entities.engineer.BioMedical;

public interface BioMedicalService {

	BioMedical createUser(BioMedical user);

	List<BioMedical> getAllUsers();

	void deleteUser(String jobField) throws Exception;

	BioMedical updateUser(BioMedical userDto, String jobField) throws Exception;

	BioMedical getUserById(String jobField) throws Exception;

}
