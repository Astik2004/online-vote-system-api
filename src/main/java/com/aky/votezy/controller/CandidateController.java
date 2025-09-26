package com.aky.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aky.votezy.entity.Candidate;
import com.aky.votezy.service.CandidateService;

import jakarta.validation.Valid;

/**
 * CandidateController handles HTTP requests related to candidate operations such as adding, retrieving, updating, and deleting candidates.<br>
 * <b>Author : Er.Astik Yadav</b>
 */

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {
	private CandidateService candidateService;
	
	@Autowired
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	@PostMapping("/add")
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
		Candidate newCandidate = candidateService.addCandidate(candidate);
		return new ResponseEntity<>(newCandidate, HttpStatus.CREATED);
	}
	@GetMapping()
	public ResponseEntity<List<Candidate>> getAllCandidates() {
		List<Candidate> candidates = candidateService.getAllCandidates();
		return new ResponseEntity<>(candidates, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
		Candidate candidate = candidateService.getCandidateById(id);
		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {
		Candidate candidate = candidateService.updateCandidate(id, updatedCandidate);
		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
		candidateService.deleteCandidate(id);
		return new ResponseEntity<>("Candidate with id "+id+" Deleted",HttpStatus.OK);
	}
}
