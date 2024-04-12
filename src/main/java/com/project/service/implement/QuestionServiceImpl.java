package com.project.service.implement;


import com.project.entities.Questions;
import com.project.entities.User;
import com.project.repo.QuestionRepository;
import com.project.repo.UserRepository;
import com.project.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private UserRepository userRepository;
	
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Questions> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Questions> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void addQuestion(Questions question) {
        questionRepository.save(question);
    }

    @Override
    public List<Questions> getQuestionsByUserId(Long userId) {
        return questionRepository.findByUserUserId(userId);
    }
   
    
   
}
