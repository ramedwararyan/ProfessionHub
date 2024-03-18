package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.Electrical;

public interface ElectricalRepo extends BaseRepository<Electrical> {
	List<Electrical> findByJobField(String jobField);
}