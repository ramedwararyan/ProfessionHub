package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.Mechanical;
import com.project.repo.engineer.MechanicalRepo;
import com.project.service.engineer.MechanicalService;

@Service
public class MechanicalImpl implements MechanicalService {

	@Autowired
	private MechanicalRepo userRepo;

	@Override
	public Mechanical createUser(Mechanical userDto) {
		Mechanical user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<Mechanical> getAllUsers() {
		List<Mechanical> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public Mechanical updateUser(Mechanical userDto, String jobField) throws Exception {
		List<Mechanical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Mechanical user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		Mechanical updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public Mechanical getUserById(String jobField) throws Exception {
		List<Mechanical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<Mechanical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Mechanical user = users.get(0);
		this.userRepo.delete(user);
	}

}
