package com.aky.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aky.votezy.entity.Voter;
import com.aky.votezy.service.VoterService;
import jakarta.validation.Valid;

/**
 * VoterController handles HTTP requests related to voter operations such as registration, retrieval, and updates.<br>
 * <b>Author : Er.Astik Yadav</b>
 */

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
	// Service layer for voter operations
	private VoterService voterService;
	
	// Constructor-based dependency injection
	@Autowired
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
	
	// Endpoint to register a new voter
	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter) {
		Voter registeredVoter = voterService.registerVoter(voter);
		return new ResponseEntity<>(registeredVoter, HttpStatus.CREATED);
	}
	
	// Endpoint to retrieve a voter by ID
	@GetMapping("/{id}")
	public ResponseEntity<Voter> getVoterById(@PathVariable Long id) {
		Voter voter = voterService.getVoterById(id);
		return new ResponseEntity<>(voter, HttpStatus.OK);
	}
	
	// Endpoint to retrieve all voters
	@GetMapping()
	public ResponseEntity<List<Voter>> getAllVoters() {
		List<Voter> voters = voterService.getAllVoters();
		return new ResponseEntity<>(voters, HttpStatus.OK);
	}
	
	// Endpoint to update voter details
	@PutMapping("/update/{id}")
	public ResponseEntity<Voter> updateVoter(@PathVariable Long id, @RequestBody Voter voter) {
		Voter updatedVoter = voterService.updateVoter(id, voter);
		return new ResponseEntity<>(updatedVoter, HttpStatus.OK);
	}
	
	// Endpoint to delete a voter by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVoter(@PathVariable Long id) {
		voterService.deleteVoter(id);
		return new ResponseEntity<>("Voter with Id "+id+" Deleted",HttpStatus.OK);
	}
}
