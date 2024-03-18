package com.project.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.Answers;
import com.project.entities.Questions;
import com.project.repo.AnswerRepository;

public interface AnswerService {

    void addAnswer(Answers answer);

  
    public List<Answers> getAllAnswersForQuestion(Long questionId);
}

