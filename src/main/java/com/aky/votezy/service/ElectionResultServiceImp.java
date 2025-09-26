package com.aky.votezy.service;
import java.util.List;
import java.util.Optional;
import com.aky.votezy.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.aky.votezy.entity.Candidate;
import com.aky.votezy.entity.ElectionResult;
import com.aky.votezy.exception.ResourceNotFoundException;
import com.aky.votezy.repository.CandidateRepository;
import com.aky.votezy.repository.ElectionResultRepository;
import com.aky.votezy.repository.VoteRepository;

@RestController
public class ElectionResultServiceImp implements ElectionResultService {

    private final VoterRepository voterRepository;
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
	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult> existingResult = electionResultRepository.findByElectionName(electionName);
		if(existingResult.isPresent()) {
			return existingResult.get();
		}
		if(voterRepository.count()==0)
		{
			throw new IllegalStateException("Cannot declare result: No voters found.");
		}
		List<Candidate> allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		if(allCandidates.isEmpty()) {
			throw new ResourceNotFoundException("Cannot declare result: No candidates found.");
		}
		Candidate winner = allCandidates.get(0);
		int totalVotes=0;
		for(Candidate candidate: allCandidates) {
			totalVotes+=candidate.getVoteCount();
		}
		ElectionResult electionResult = new ElectionResult();
		electionResult.setElectionName(electionName);
		electionResult.setTotalVotes(totalVotes);
		electionResult.setWinner(winner);
		return electionResultRepository.save(electionResult);
	}
	@Override
	public List<ElectionResult> getAllElectionResults() {
		return electionResultRepository.findAll();
	}
	
}
