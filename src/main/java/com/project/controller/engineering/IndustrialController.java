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

import com.project.entities.engineer.Industrial;
import com.project.service.FileService;
import com.project.service.engineer.IndustrialService;

import jakarta.validation.Valid;

@Controller
public class IndustrialController {

	@Autowired
	private IndustrialService service;

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// create
	@PostMapping("/industrial/createuser")
	public ResponseEntity<Industrial> createUser(@Valid @RequestBody Industrial userDto) {
		Industrial createUserDto = service.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// get-all user
	@GetMapping("/industrial/users")
	public ResponseEntity<List<Industrial>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	// put-update
	@PutMapping("/industrial/put/{jobField}")
	public ResponseEntity<Industrial> updateUser(@Valid @RequestBody Industrial userDto, @PathVariable String jobField)
			throws Exception {
		Industrial updatedUser = service.updateUser(userDto, jobField);
		return ResponseEntity.ok(updatedUser);
	}

	// get single id
	@GetMapping("/industrial/single/{jobField}")
	public ResponseEntity<Industrial> getSingleUser(@PathVariable("jobField") String jobField) throws Exception {
		return ResponseEntity.ok(service.getUserById(jobField));
	}

	// delete
	@DeleteMapping("/industrial/delete/{jobField}")
	public ResponseEntity<Object> deleteUser(@PathVariable String jobField) {
		try {
			service.deleteUser(jobField);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

	// file upload
	@PostMapping("/industrial/file/upload/{jobField}")
	public ResponseEntity<Industrial> uploadPostImage(@RequestParam("file") MultipartFile image,
			@PathVariable String jobField) throws Exception {
		Industrial postDto = service.getUserById(jobField);
		String fileName = fileService.uploadImage(path, image);
		postDto.setFile(fileName);
		Industrial updatePost = service.updateUser(postDto, jobField);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
}
