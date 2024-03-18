package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.Mechanical;

public interface MechanicalRepo extends BaseRepository<Mechanical> {
	List<Mechanical> findByJobField(String jobField);
}
