package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.ComputerScience;
import com.project.repo.engineer.ComputerScienceRepo;
import com.project.service.engineer.ComputerScienceService;

@Service
public class ComputerScienceImpl implements ComputerScienceService {

	@Autowired
	private ComputerScienceRepo userRepo;

	@Override
	public ComputerScience createUser(ComputerScience userDto) {
		ComputerScience user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<ComputerScience> getAllUsers() {
		List<ComputerScience> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public ComputerScience updateUser(ComputerScience userDto, String jobField) throws Exception {
		List<ComputerScience> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		ComputerScience user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		ComputerScience updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public ComputerScience getUserById(String jobField) throws Exception {
		List<ComputerScience> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<ComputerScience> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		ComputerScience user = users.get(0);
		this.userRepo.delete(user);
	}

}
