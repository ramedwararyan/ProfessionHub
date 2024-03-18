package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.Civil;
import com.project.repo.engineer.CivilRepository;
import com.project.service.engineer.CivilService;

@Service
public class CivilImpl implements CivilService {

	@Autowired
	private CivilRepository userRepo;

	@Override
	public Civil createUser(Civil userDto) {
		Civil user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<Civil> getAllUsers() {
		List<Civil> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public Civil updateUser(Civil userDto, String jobField) throws Exception {
		List<Civil> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Civil user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		Civil updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public Civil getUserById(String jobField) throws Exception {
		List<Civil> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<Civil> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Civil user = users.get(0);
		this.userRepo.delete(user);
	}

}
