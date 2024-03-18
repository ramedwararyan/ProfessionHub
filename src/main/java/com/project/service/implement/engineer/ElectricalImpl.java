package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.Electrical;

import com.project.repo.engineer.ElectricalRepo;
import com.project.service.engineer.ElectricalService;

@Service
public class ElectricalImpl implements ElectricalService {

	@Autowired
	private ElectricalRepo userRepo;

	@Override
	public Electrical createUser(Electrical userDto) {
		Electrical user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<Electrical> getAllUsers() {
		List<Electrical> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public Electrical updateUser(Electrical userDto, String jobField) throws Exception {
		List<Electrical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Electrical user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		Electrical updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public Electrical getUserById(String jobField) throws Exception {
		List<Electrical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<Electrical> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Electrical user = users.get(0);
		this.userRepo.delete(user);
	}
}
