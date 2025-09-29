package com.aky.votezy.service;
import java.util.List;
import java.util.Optional;
import com.aky.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.aky.votezy.entity.Candidate;
import com.aky.votezy.entity.ElectionResult;
import com.aky.votezy.entity.Voter;
import com.aky.votezy.exception.ResourceNotFoundException;
import com.aky.votezy.repository.CandidateRepository;
import com.aky.votezy.repository.ElectionResultRepository;
import com.aky.votezy.repository.VoteRepository;

@RestController
public class ElectionResultServiceImp implements ElectionResultService {

    private VoterRepository voterRepository;
	private ElectionResultRepository electionResultRepository;
	private CandidateRepository candidateRepository;
	private VoteRepository voteRepository;   
	
	@Autowired
	public ElectionResultServiceImp(ElectionResultRepository electionResultRepository,
			CandidateRepository candidateRepository, VoteRepository voteRepository, VoterRepository voterRepository) {
		super();
		this.electionResultRepository = electionResultRepository;
		this.candidateRepository = candidateRepository;
		this.voteRepository = voteRepository;
		this.voterRepository = voterRepository;
	}
	
	@Override
	@Transactional
	public ElectionResult declareElectionResult(String electionName) {
	    // Check if result already exists for the given election name
	    Optional<ElectionResult> existingResult = electionResultRepository.findByElectionName(electionName);
	    if(existingResult.isPresent()) {
	        return existingResult.get(); // Keep history - don’t overwrite
	    }

	    // Check if voters exist
	    if(voterRepository.count() == 0) {
	        throw new IllegalStateException("Cannot declare result: No voters found.");
	    }

	    // Get candidates sorted by votes
	    List<Candidate> allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
	    if(allCandidates.isEmpty()) {
	        throw new ResourceNotFoundException("Cannot declare result: No candidates found.");
	    }

	    // Calculate winner and total votes
	    Candidate winner = allCandidates.get(0);
	    int totalVotes = allCandidates.stream()
	            .mapToInt(Candidate::getVoteCount)
	            .sum();

	    // Save result in DB
	    ElectionResult electionResult = new ElectionResult();
	    electionResult.setElectionName(electionName);
	    electionResult.setTotalVotes(totalVotes);
	    electionResult.setWinner(winner);
	    electionResultRepository.save(electionResult);

	    // Reset voters isVoted = false
	    List<Voter> voters = voterRepository.findAll();
	    for (Voter voter : voters) {
	        voter.setHasVoted(false);
	    }
	    voterRepository.saveAll(voters);

	    // Reset candidates voteCount = 0
	    for (Candidate candidate : allCandidates) {
	        candidate.setVoteCount(0);
	    }
	    candidateRepository.saveAll(allCandidates);

	    return electionResult;
	}
	@Override
	public List<ElectionResult> getAllElectionResults() {
		return electionResultRepository.findAll();
	}
	
}
