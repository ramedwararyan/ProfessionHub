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

import com.project.entities.engineer.Chemical;
import com.project.service.FileService;
import com.project.service.engineer.ChemicalService;

import jakarta.validation.Valid;

@Controller
public class ChemicalController {

	@Autowired
	private ChemicalService service;

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// create
	@PostMapping("/chemical/createuser")
	public ResponseEntity<Chemical> createUser(@Valid @RequestBody Chemical userDto) {
		Chemical createUserDto = service.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// get-all user
	@GetMapping("/chemical/users")
	public ResponseEntity<List<Chemical>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	// put-update
	@PutMapping("/chemical/put/{jobField}")
	public ResponseEntity<Chemical> updateUser(@Valid @RequestBody Chemical userDto, @PathVariable String jobField)
			throws Exception {
		Chemical updatedUser = service.updateUser(userDto, jobField);
		return ResponseEntity.ok(updatedUser);
	}

	// get single id
	@GetMapping("/chemical/single/{jobField}")
	public ResponseEntity<Chemical> getSingleUser(@PathVariable("jobField") String jobField) throws Exception {
		return ResponseEntity.ok(service.getUserById(jobField));
	}

	// delete
	@DeleteMapping("/chemical/delete/{jobField}")
	public ResponseEntity<Object> deleteUser(@PathVariable String jobField) {
		try {
			service.deleteUser(jobField);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

	// file upload
	@PostMapping("/chemical/file/upload/{jobField}")
	public ResponseEntity<Chemical> uploadPostImage(@RequestParam("file") MultipartFile image,
			@PathVariable String jobField) throws Exception {
		Chemical postDto = service.getUserById(jobField);
		String fileName = fileService.uploadImage(path, image);
		postDto.setFile(fileName);
		Chemical updatePost = service.updateUser(postDto, jobField);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
}
