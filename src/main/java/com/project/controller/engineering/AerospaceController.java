package com.project.controller.engineering;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.entities.engineer.Aerospace;
import com.project.repo.engineer.AerospaceRepository;
import com.project.service.FileService;
import com.project.service.engineer.AerospaceService;

import jakarta.validation.Valid;

@Controller
public class AerospaceController {

	@Autowired
	private AerospaceRepository userRepo;

	
	@Autowired
	private AerospaceService aerospaceService;

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// create
	@PostMapping("/aerospace/createuser")
	public ResponseEntity<Aerospace> createUser(@Valid @RequestBody Aerospace userDto) {
		Aerospace createUserDto = aerospaceService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// get-all user
	@GetMapping("/aerospace/users")
	public ResponseEntity<List<Aerospace>> getAllUsers() {
		return ResponseEntity.ok(aerospaceService.getAllUsers());
	}

	// put-update
	@PutMapping("/aerospace/put/{jobField}")
	public ResponseEntity<Aerospace> updateUser(@Valid @RequestBody Aerospace userDto, @PathVariable String jobField)
			throws Exception {
		Aerospace updatedUser = aerospaceService.updateUser(userDto, jobField);
		return ResponseEntity.ok(updatedUser);
	}

	// get single id
	@GetMapping("/aerospace/single/{jobField}")
	public ResponseEntity<Aerospace> getSingleUser(@PathVariable("jobField") String jobField) throws Exception {
		return ResponseEntity.ok(aerospaceService.getUserById(jobField));
	}

	// delete
	@DeleteMapping("/aerospace/delete/{jobField}")
	public ResponseEntity<Object> deleteUser(@PathVariable String jobField) {
		try {
			aerospaceService.deleteUser(jobField);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

	// file upload
	@PostMapping("/aerospace/file/upload/{jobField}")
	public ResponseEntity<Aerospace> uploadPostImage(@RequestParam("file") MultipartFile image,
			@PathVariable String jobField) throws Exception {
		Aerospace postDto = aerospaceService.getUserById(jobField);
		String fileName = fileService.uploadImage(path, image);
		postDto.setFile(fileName);
		Aerospace updatePost = aerospaceService.updateUser(postDto, jobField);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
	
	@GetMapping("/aero")
	public String showAerospaceInfo(Model model) {
	    List<Aerospace> aerospaceList = userRepo.findAll(); // Replace findAll() with your desired logic
	    model.addAttribute("aeroList", aerospaceList);
	    return "file"; // Thymeleaf template name
	}
	
	@GetMapping("/aero/{jobField}")
	public String showAerospaceInfo(@PathVariable("jobField") String jobField, Model model) {
	    List<Aerospace> aero = userRepo.findByJobField(jobField);
	    model.addAttribute("aeroList", aero);
	    return "file";
	}



}
