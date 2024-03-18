package com.project.service;



import com.project.entities.Questions;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Questions> getAllQuestions();

    Optional<Questions> getQuestionById(Long id);

    void addQuestion(Questions question);

    // Add more methods as needed (e.g., deleteQuestion, updateQuestion, etc.)
}


