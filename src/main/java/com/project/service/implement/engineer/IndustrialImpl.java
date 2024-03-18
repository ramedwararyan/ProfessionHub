package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.Industrial;
import com.project.repo.engineer.IndustrialRepo;
import com.project.service.engineer.IndustrialService;

@Service
public class IndustrialImpl implements IndustrialService {

	@Autowired
	private IndustrialRepo userRepo;

	@Override
	public Industrial createUser(Industrial userDto) {
		Industrial user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<Industrial> getAllUsers() {
		List<Industrial> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public Industrial updateUser(Industrial userDto, String jobField) throws Exception {
		List<Industrial> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Industrial user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		Industrial updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public Industrial getUserById(String jobField) throws Exception {
		List<Industrial> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<Industrial> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Industrial user = users.get(0);
		this.userRepo.delete(user);
	}
}
