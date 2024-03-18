package com.project.controller;




import com.project.dto.UserDto;
import com.project.entities.Answers;
import com.project.entities.Questions;
import com.project.entities.User;
import com.project.service.AnswerService;
import com.project.service.QuestionService;
import com.project.service.UserService;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
    private  QuestionService questionService;
	
	@Autowired
	private UserService userService;
  
	 @Autowired
	    private AnswerService answerService;

	
    @PostMapping("/ask")
    public String processQuestionForm(@ModelAttribute Questions question,Model model,Principal principal) {
    	  UserDto currentUser = userService.getCurrentUser();
          
          // Set the current user to the question
          question.setUser(userService.dtoToUser(currentUser));
          
    	List<Questions> questions = questionService.getAllQuestions();
	    model.addAttribute("questions", questions);
    	questionService.addQuestion(question);    
        return "allQuestionPage"; // Redirect to the questions page or another appropriate page
    }
    

  		
  		 //get-user
  		@GetMapping("/question")
  		public String getAllQuestions(@ModelAttribute Questions question,Model model){
  			List<Questions> questions = questionService.getAllQuestions();
  		    model.addAttribute("questions", questions);
  	        return "allQuestionPage";
  		}
  		
  		@GetMapping("/answers")
  		public String showAllAnswersForQuestion(@RequestParam("questionId") Long questionId, Model model) {
  		    List<Answers> answers = answerService.getAllAnswersForQuestion(questionId);
  		    model.addAttribute("answers", answers);
  		  Optional<Questions> optionalQuestion = questionService.getQuestionById(questionId);
         
              Questions question = optionalQuestion.get();
  		  model.addAttribute("questionText", question.getQuestiontext());
  		    return "answer-page";
  		}
}

