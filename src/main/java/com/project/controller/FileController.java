package com.project.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.project.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	@Value("${project.file}")
	private String path;

	// New method for serving PDF files
	@GetMapping(value = "/file/{pdfName}", produces = MediaType.APPLICATION_PDF_VALUE)
	public void downloadPdf(@PathVariable("pdfName") String pdfName, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResource(path, pdfName);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Content-Disposition", "inline; filename=" + pdfName);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
