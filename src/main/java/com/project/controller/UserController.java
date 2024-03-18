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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dto.UserDto;
import com.project.entities.engineer.Aerospace;
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

	
	//post-create
		@PostMapping("/postuser")
		public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
			UserDto createUserDto = this.userService.createUser(userDto);
			return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
			
		}
			
		
		
		
		//put-update
		@PutMapping("/{userId}")
		public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Long userId){
			UserDto updatedUser = this.userService.updateUser(userDto, userId);
			return ResponseEntity.ok(updatedUser);
		}
		
		//put-update
				@GetMapping("/edit/{userId}")
				public String updateUser1(@Valid @RequestBody UserDto userDto,@PathVariable Long userId){
					
					return "edit-page";
				}
		
		
		//delete
		
		@DeleteMapping("/{userId}")
		public void deleteUser(@PathVariable Long userId){
			this.userService.deleteUser(userId);
		
		}
		
		//get-user
		@GetMapping("/getAll")
		public ResponseEntity<List<UserDto>> getAllUsers(){
			return ResponseEntity.ok(this.userService.getAllUsers());
		}
		
		@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Long uid){
			return ResponseEntity.ok(this.userService.getUserById(uid));
		}
		
		 @GetMapping("/api/user")
		    public String getUsername() {
		        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        return authentication.getName();
		    }
}
