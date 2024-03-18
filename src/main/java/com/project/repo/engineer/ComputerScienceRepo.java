package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.ComputerScience;

public interface ComputerScienceRepo extends BaseRepository<ComputerScience> {
	List<ComputerScience> findByJobField(String jobField);
}
