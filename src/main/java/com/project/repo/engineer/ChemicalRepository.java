package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.Chemical;

public interface ChemicalRepository extends BaseRepository<Chemical> {
	List<Chemical> findByJobField(String jobField);
}
