package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}

