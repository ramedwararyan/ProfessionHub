package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.Chemical;
import com.project.repo.engineer.ChemicalRepository;
import com.project.service.engineer.ChemicalService;

@Service
public class ChemicalImpl implements ChemicalService {

	@Autowired
	private ChemicalRepository userRepo;

	@Override
	public Chemical createUser(Chemical userDto) {
		Chemical user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<Chemical> getAllUsers() {
		List<Chemical> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public Chemical updateUser(Chemical userDto, String jobField) throws Exception {
		List<Chemical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Chemical user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		Chemical updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public Chemical getUserById(String jobField) throws Exception {
		List<Chemical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<Chemical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Chemical user = users.get(0);
		this.userRepo.delete(user);
	}

}
