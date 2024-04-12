package com.project.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.entities.FileEntity;
import com.project.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class FileUploader {

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;
	
	@PostMapping("/upload")
	public String uploadImage(
			
			 @RequestParam("name") String name,
	            @RequestParam("email") String email,
	            @RequestParam("title") String title,
	            @RequestParam("description") String description,
			@RequestParam("file") MultipartFile file
			) throws IOException {

		 String fileName = fileService.uploadImage(path, file);

		 FileEntity fileEntity = new FileEntity();
         fileEntity.setName(name);
         fileEntity.setEmail(email);
         fileEntity.setTitle(title);
         fileEntity.setDescription(description);

		 
         // Set file details
         fileEntity.setFile(fileName);

         // Save file details to the database
         fileService.saveFileDetails(fileEntity);
		
		
	
		return "index2";

	}
	

    
}
