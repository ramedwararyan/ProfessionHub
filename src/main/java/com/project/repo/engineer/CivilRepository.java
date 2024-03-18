package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.Civil;

public interface CivilRepository extends BaseRepository<Civil> {
	List<Civil> findByJobField(String jobField);
}
