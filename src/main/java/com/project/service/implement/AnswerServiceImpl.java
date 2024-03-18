package com.project.service.implement;



import com.project.entities.Answers;
import com.project.entities.Questions;
import com.project.repo.AnswerRepository;
import com.project.repo.QuestionRepository;
import com.project.service.AnswerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

	private QuestionRepository questionRepository;
	
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void addAnswer(Answers answer) {
        answerRepository.save(answer);
    }

   

    @Override
    public List<Answers> getAllAnswersForQuestion(Long questionId) {
        return answerRepository.findByQuestionQuestionId(questionId);
    }
}
