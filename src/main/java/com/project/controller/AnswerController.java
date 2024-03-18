package com.project.controller;


import com.project.entities.Answers;
import com.project.entities.Questions;
import com.project.service.AnswerService;
import com.project.service.QuestionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @Autowired
    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping("/questions/reply")
    public String showReplyForm(@RequestParam("questionId") Long questionId, Model model) {
    	Optional<Questions> optionalQuestion = questionService.getQuestionById(questionId);
        if (optionalQuestion.isPresent()) {
            Questions question = optionalQuestion.get();
        model.addAttribute("questionId", questionId);
        model.addAttribute("questionText", question.getQuestiontext());
        return "reply-form"; } else {
            // Handle the case where the question with the given ID is not found
            // You can redirect to an error page or handle it in another way
            return "question-not-found"; // Example: redirect to a page indicating question not found
        }
    }

    @PostMapping("/answers/submit")
    public String submitAnswer(@RequestParam("questionId") Long questionId, @RequestParam("answer") String answerText, Model model) {
    	Optional<Questions> optionalQuestion = questionService.getQuestionById(questionId);
        if (optionalQuestion.isPresent()) {
            Questions question = optionalQuestion.get();
        Answers answer = new Answers();
        answer.setAnswerText(answerText);
        answer.setQuestion(question);
        answerService.addAnswer(answer);
        // Redirect to some confirmation page or wherever you want to go after submitting answer
        model.addAttribute("questionText", question.getQuestiontext());
        model.addAttribute("answerText", answerText);
        List<Answers> answers = answerService.getAllAnswersForQuestion(questionId);
		    model.addAttribute("answers", answers);
        return "answer-page"; } else {
            // Handle the case where the question with the given ID is not found
            // You can redirect to an error page or handle it in another way
            return "question-not-found"; // Example: redirect to a page indicating question not found
        }
    }
}
