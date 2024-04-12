package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

	 @PostMapping("/search")
	    public String search(@RequestParam("query") String query) {
		 String[] words = query.trim().split("\\s+"); // Split the input query into words
		    String firstWord = words[0].toLowerCase(); // Convert the first word to lowercase

		    switch (firstWord) {
		        case "fullstack":
		            return "redirect:/explore10"; // Redirect to the page for fullstack
		        case "defence":
		            return "redirect:/explore2"; // Redirect to the page for defence
		        case "3d":
		            return "redirect:/explore3"; // Redirect to the page for 3D animation
		        default:
		            return "redirect:/error"; // Redirect to an error page for unknown queries
		    }
	}

}

