package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.BioMedical;

public interface BioMedicalRepository extends BaseRepository<BioMedical> {
	List<BioMedical> findByJobField(String jobField);
}