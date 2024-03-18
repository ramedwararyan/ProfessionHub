package com.project.repo;


import com.project.entities.Answers;
import com.project.entities.Questions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {

	List<Answers> findByQuestionQuestionId(Long questionId);
}
