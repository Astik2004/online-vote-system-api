package com.aky.votezy.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aky.votezy.dto.VoteRequestDTO;
import com.aky.votezy.dto.VoteResponseDTO;
import com.aky.votezy.entity.Vote;
import com.aky.votezy.service.VotingService;
import jakarta.validation.Valid;

/**
 * VotingController handles HTTP requests related to voting operations such as casting votes and retrieving vote records.<br>
 * <b>Author : Er.Astik Yadav</b>
 */

@RestController
@RequestMapping("/api/votes")
public class VotingController {
	private VotingService votingService;
	
	// Constructor-based dependency injection
	@Autowired
	public VotingController(VotingService votingService) {
		this.votingService = votingService;
	}
	
	// Endpoint to cast a vote
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO) {
		Vote vote = votingService.castVote(voteRequestDTO.getVoterId(), voteRequestDTO.getCandidateId());
		VoteResponseDTO responseDTO = new VoteResponseDTO("Vote cast successfully", true, vote.getVoterId(), vote.getCandidateId());
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	// Endpoint to retrieve all votes
	@GetMapping("/all/votes")
	public ResponseEntity<List<Vote>> getAllVotes() {
		List<Vote> votes = votingService.getAllVotes();
		return new ResponseEntity<>(votes, HttpStatus.OK);
	}
}
