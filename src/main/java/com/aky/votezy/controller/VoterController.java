package com.aky.votezy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aky.votezy.entity.Voter;
import com.aky.votezy.service.VoterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
	
	private VoterService voterService;
	
	@Autowired
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter) {
		Voter registeredVoter = voterService.registerVoter(voter);
		return new ResponseEntity<>(registeredVoter, HttpStatus.CREATED);
	}
}
