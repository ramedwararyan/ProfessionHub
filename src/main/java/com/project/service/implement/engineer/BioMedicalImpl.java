package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.BioMedical;
import com.project.repo.engineer.BioMedicalRepository;
import com.project.service.engineer.BioMedicalService;

@Service
public class BioMedicalImpl implements BioMedicalService {

	@Autowired
	private BioMedicalRepository userRepo;

	@Override
	public BioMedical createUser(BioMedical userDto) {
		BioMedical user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<BioMedical> getAllUsers() {
		List<BioMedical> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public BioMedical updateUser(BioMedical userDto, String jobField) throws Exception {
		List<BioMedical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		BioMedical user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		BioMedical updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public BioMedical getUserById(String jobField) throws Exception {
		List<BioMedical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<BioMedical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		BioMedical user = users.get(0);
		this.userRepo.delete(user);
	}

}
