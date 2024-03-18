package com.project.service.implement.engineer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.engineer.Aerospace;
import com.project.repo.engineer.AerospaceRepository;
import com.project.service.engineer.AerospaceService;

@Service
public class AeroSpaceImpl implements AerospaceService {

	@Autowired
	private AerospaceRepository userRepo;

	@Override
	public Aerospace createUser(Aerospace userDto) {
		Aerospace user = this.userRepo.save(userDto);
		return user;
	}

	@Override
	public List<Aerospace> getAllUsers() {
		List<Aerospace> users = this.userRepo.findAll();
		return users;
	}

	@Override
	public Aerospace updateUser(Aerospace userDto, String jobField) throws Exception {
		List<Aerospace> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Aerospace user = users.get(0);

		user.setJobField(userDto.getJobField());
		user.setFile(userDto.getFile());

		Aerospace updatedUser = this.userRepo.save(user);
		return updatedUser;
	}

	@Override
	public Aerospace getUserById(String jobField) throws Exception {
		List<Aerospace> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		return users.get(0);
	}

	@Override
	public void deleteUser(String jobField) throws Exception {
		List<Aerospace> users = this.userRepo.findByJobField(jobField);

		if (users.isEmpty()) {
			throw new Exception("User not found with jobField: " + jobField);
		}

		Aerospace user = users.get(0);
		this.userRepo.delete(user);
	}
}
