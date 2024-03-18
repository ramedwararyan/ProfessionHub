package com.project.controller.engineering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.entities.engineer.Mechanical;
import com.project.service.FileService;
import com.project.service.engineer.MechanicalService;

import jakarta.validation.Valid;

@Controller
public class MechanicalController {

	@Autowired
	private MechanicalService service;

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// create
	@PostMapping("/mechanical/createuser")
	public ResponseEntity<Mechanical> createUser(@Valid @RequestBody Mechanical userDto) {
		Mechanical createUserDto = service.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// get-all user
	@GetMapping("/mechanical/users")
	public ResponseEntity<List<Mechanical>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	// put-update
	@PutMapping("/mechanical/put/{jobField}")
	public ResponseEntity<Mechanical> updateUser(@Valid @RequestBody Mechanical userDto, @PathVariable String jobField)
			throws Exception {
		Mechanical updatedUser = service.updateUser(userDto, jobField);
		return ResponseEntity.ok(updatedUser);
	}

	// get single id
	@GetMapping("/mechanical/single/{jobField}")
	public ResponseEntity<Mechanical> getSingleUser(@PathVariable("jobField") String jobField) throws Exception {
		return ResponseEntity.ok(service.getUserById(jobField));
	}

	// delete
	@DeleteMapping("/mechanical/delete/{jobField}")
	public ResponseEntity<Object> deleteUser(@PathVariable String jobField) {
		try {
			service.deleteUser(jobField);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

	// file upload
	@PostMapping("/mechanical/file/upload/{jobField}")
	public ResponseEntity<Mechanical> uploadPostImage(@RequestParam("file") MultipartFile image,
			@PathVariable String jobField) throws Exception {
		Mechanical postDto = service.getUserById(jobField);
		String fileName = fileService.uploadImage(path, image);
		postDto.setFile(fileName);
		Mechanical updatePost = service.updateUser(postDto, jobField);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
}
