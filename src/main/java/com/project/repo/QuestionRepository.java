package com.project.repo;


import com.project.entities.Questions;
import com.project.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
    // You can add custom query methods if needed
	 
	 
		
}

