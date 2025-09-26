package com.aky.votezy.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aky.votezy.dto.ElectionResultRequestDTO;
import com.aky.votezy.dto.ElectionResultResponseDTO;
import com.aky.votezy.entity.ElectionResult;
import com.aky.votezy.service.ElectionResultService;

/**
 * REST controller for managing election results.
 * <b>Author : Er.Astik Yadav</b>
 */

@RestController
@RequestMapping("/api/election-results")
public class ElectionResultController {
	private ElectionResultService electionResultService;
	
	@Autowired
	public ElectionResultController(ElectionResultService electionResultService) {
		this.electionResultService = electionResultService;
	}
	
	// Endpoint to declare election result
	@PostMapping("/declare")
	public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody ElectionResultRequestDTO requestDTO) {
		ElectionResult result = electionResultService.declareElectionResult(requestDTO.getElectionName());
		ElectionResultResponseDTO resultDTO = new ElectionResultResponseDTO();
		resultDTO.setElectionName(result.getElectionName());
		resultDTO.setTotalVotes(result.getTotalVotes());
		resultDTO.setWinnerId(result.getWinnerId());
		resultDTO.setWinnerName(result.getWinner() != null ? result.getWinner().getName() : null);
		resultDTO.setWinnerVoteCount(result.getWinner() != null ? result.getWinner().getVoteCount() : 0);
		return ResponseEntity.ok(resultDTO);
	}
	
	// Endpoint to get all election results
	@GetMapping()
	public ResponseEntity<List<ElectionResult>> getAllElectionResults() {
		List<ElectionResult> results = electionResultService.getAllElectionResults();
		return ResponseEntity.ok(results);
	}
}
