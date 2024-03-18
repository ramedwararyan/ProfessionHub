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

import com.project.entities.engineer.Electrical;
import com.project.service.FileService;
import com.project.service.engineer.ElectricalService;

import jakarta.validation.Valid;

@Controller
public class ElectricalController {

	@Autowired
	private ElectricalService service;

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// create
	@PostMapping("/electrical/createuser")
	public ResponseEntity<Electrical> createUser(@Valid @RequestBody Electrical userDto) {
		Electrical createUserDto = service.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// get-all user
	@GetMapping("/electrical/users")
	public ResponseEntity<List<Electrical>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	// put-update
	@PutMapping("/electrical/put/{jobField}")
	public ResponseEntity<Electrical> updateUser(@Valid @RequestBody Electrical userDto, @PathVariable String jobField)
			throws Exception {
		Electrical updatedUser = service.updateUser(userDto, jobField);
		return ResponseEntity.ok(updatedUser);
	}

	// get single id
	@GetMapping("/electrical/single/{jobField}")
	public ResponseEntity<Electrical> getSingleUser(@PathVariable("jobField") String jobField) throws Exception {
		return ResponseEntity.ok(service.getUserById(jobField));
	}

	// delete
	@DeleteMapping("/electrical/delete/{jobField}")
	public ResponseEntity<Object> deleteUser(@PathVariable String jobField) {
		try {
			service.deleteUser(jobField);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

	// file upload
	@PostMapping("/electrical/file/upload/{jobField}")
	public ResponseEntity<Electrical> uploadPostImage(@RequestParam("file") MultipartFile image,
			@PathVariable String jobField) throws Exception {
		Electrical postDto = service.getUserById(jobField);
		String fileName = fileService.uploadImage(path, image);
		postDto.setFile(fileName);
		Electrical updatePost = service.updateUser(postDto, jobField);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
}
