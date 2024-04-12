package com.project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dto.UserDto;
import com.project.entities.User;

import com.project.repo.UserRepository;
import com.project.security.CustomUserDetail;
import com.project.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "login";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly!");
		return "login";
	}
	
	@GetMapping("/dologin")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/user-page")
	public String userPage () {
		return "index";
	}
	
	@GetMapping("/profile")
	public String viewProfile(Model model) {
	    UserDto userDto = userService.getCurrentUser();

	    if (userDto != null) {
	        model.addAttribute("user", userDto);
	        return "profile";
	    } else {
	        // Handle the case when the user is not authenticated
	        return "redirect:/login"; // Redirect to the login page or handle accordingly
	    }
	}



	    // Handle form submission for updating user
	    @PostMapping("/edit/{userId}")
	    public String updateUser(@PathVariable("userId") Long userId, @ModelAttribute("user") UserDto userDto) {
	        userService.updateUser(userId, userDto);
	        return "profile";
	    }
	
		

	
}
