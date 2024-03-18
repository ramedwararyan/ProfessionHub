package com.project.repo.engineer;

import java.util.List;

import com.project.entities.engineer.Aerospace;

public interface AerospaceRepository extends BaseRepository<Aerospace> {
    List<Aerospace> findByJobField(String jobField);
}
