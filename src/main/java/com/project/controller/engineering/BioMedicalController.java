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

import com.project.entities.engineer.BioMedical;
import com.project.service.FileService;
import com.project.service.engineer.BioMedicalService;

import jakarta.validation.Valid;

@Controller
public class BioMedicalController {

	@Autowired
	private BioMedicalService service;

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// create
	@PostMapping("/biomedical/createuser")
	public ResponseEntity<BioMedical> createUser(@Valid @RequestBody BioMedical userDto) {
		BioMedical createUserDto = service.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// get-all user
	@GetMapping("/biomedical/users")
	public ResponseEntity<List<BioMedical>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	// put-update
	@PutMapping("/biomedical/put/{jobField}")
	public ResponseEntity<BioMedical> updateUser(@Valid @RequestBody BioMedical userDto, @PathVariable String jobField)
			throws Exception {
		BioMedical updatedUser = service.updateUser(userDto, jobField);
		return ResponseEntity.ok(updatedUser);
	}

	// get single id
	@GetMapping("/biomedical/single/{jobField}")
	public ResponseEntity<BioMedical> getSingleUser(@PathVariable("jobField") String jobField) throws Exception {
		return ResponseEntity.ok(service.getUserById(jobField));
	}

	// delete
	@DeleteMapping("/biomedical/delete/{jobField}")
	public ResponseEntity<Object> deleteUser(@PathVariable String jobField) {
		try {
			service.deleteUser(jobField);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
		}
	}

	// file upload
	@PostMapping("/biomedical/file/upload/{jobField}")
	public ResponseEntity<BioMedical> uploadPostImage(@RequestParam("file") MultipartFile image,
			@PathVariable String jobField) throws Exception {
		BioMedical postDto = service.getUserById(jobField);
		String fileName = fileService.uploadImage(path, image);
		postDto.setFile(fileName);
		BioMedical updatePost = service.updateUser(postDto, jobField);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
}
