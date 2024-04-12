package com.project.service;



import com.project.entities.Questions;
import com.project.entities.User;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Questions> getAllQuestions();

    Optional<Questions> getQuestionById(Long id);

    void addQuestion(Questions question);

    List<Questions> getQuestionsByUserId(Long userId);
   
}


