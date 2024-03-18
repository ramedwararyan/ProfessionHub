package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.Industrial;

public interface IndustrialRepo extends BaseRepository<Industrial> {
	List<Industrial> findByJobField(String jobField);
}
