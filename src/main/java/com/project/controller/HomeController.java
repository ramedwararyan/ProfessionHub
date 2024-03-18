package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dto.UserDto;
import com.project.entities.User;
import com.project.repo.UserRepository;
import com.project.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;
	
	 @Autowired
	    private UserRepository userRepository;

	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home");
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About");
		return "about";
	}
	

	
	@GetMapping("/explore")
	public String explore () {
		return "index2";
	}
	
	@GetMapping("/explore1")
	public String explore1 () {
		return "index3";
	}
	
	@GetMapping("/explore2")
	public String explore2 () {
		return "index5";
	}
	
	@GetMapping("/explore3")
	public String explore3 () {
		return "question";
	}
	
	@GetMapping("/explore7")
	public String explore7 () {
		return "index5";
	}
	
	@GetMapping("/explore4")
	public String explore4 () {
		return "file";
	}
	
	@GetMapping("/explore5")
	public String explore5 (Model model) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String currentPrincipalName = authentication.getName();
	        User currentUser = userRepository.findByFullname(currentPrincipalName); // Assuming you have a method to find user by email
	        model.addAttribute("currentUser", currentUser);
		return "chat";
	}
	
	@GetMapping("/explore6")
	public String explore6 (Model model) {
		  UserDto userDto = userService.getCurrentUser();

		    
		  model.addAttribute("user", userDto);
		
		return "chatcomment";
	}
	
	
	
	
	
	
}
