package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.entities.engineer.Aerospace;
import com.project.entities.engineer.BioMedical;
import com.project.entities.engineer.Chemical;
import com.project.entities.engineer.Civil;
import com.project.entities.engineer.ComputerScience;
import com.project.entities.engineer.Electrical;
import com.project.entities.engineer.Industrial;
import com.project.entities.engineer.Mechanical;
import com.project.service.*;

@Controller
public class SearchController {

	@Autowired
	private GenericSearchService searchService;

	@GetMapping("/search/{jobField}")
	public ResponseEntity<List<Object>> performSearch(@PathVariable("jobField") String jobField) {
		List<Aerospace> resultsTable1 = searchService.searchByJobField(Aerospace.class, jobField);
		List<BioMedical> resultsTable2 = searchService.searchByJobField(BioMedical.class, jobField);
		List<Chemical> resultsTable3 = searchService.searchByJobField(Chemical.class, jobField);
		List<Civil> resultsTable4 = searchService.searchByJobField(Civil.class, jobField);
		List<ComputerScience> resultsTable5 = searchService.searchByJobField(ComputerScience.class, jobField);
		List<Electrical> resultsTable6 = searchService.searchByJobField(Electrical.class, jobField);
		List<Industrial> resultsTable7 = searchService.searchByJobField(Industrial.class, jobField);
		List<Mechanical> resultsTable8 = searchService.searchByJobField(Mechanical.class, jobField);

		// Combine results from different tables if needed
		List<Object> combinedResults = new ArrayList<>();
		combinedResults.addAll(resultsTable1);
		combinedResults.addAll(resultsTable2);
		combinedResults.addAll(resultsTable3);
		combinedResults.addAll(resultsTable4);
		combinedResults.addAll(resultsTable5);
		combinedResults.addAll(resultsTable6);
		combinedResults.addAll(resultsTable7);
		combinedResults.addAll(resultsTable8);
		return new ResponseEntity<>(combinedResults, HttpStatus.OK);
	}

}
