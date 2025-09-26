package com.aky.votezy.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aky.votezy.entity.Candidate;
import com.aky.votezy.entity.Vote;
import com.aky.votezy.entity.Voter;
import com.aky.votezy.exception.ResourceNotFoundException;
import com.aky.votezy.exception.VoteNotAllowedException;
import com.aky.votezy.repository.CandidateRepository;
import com.aky.votezy.repository.VoteRepository;
import com.aky.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

/**
 * VotingServiceImp handles the business logic for casting votes and retrieving vote records.<br>
 * <b>Author : Er.Astik Yadav</b>
 */

@Service
public class VotingServiceImp implements VotingService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	private VoteRepository voteRepository;
	
	// Constructor-based dependency injection
	@Autowired
	public VotingServiceImp(VoterRepository voterRepository, CandidateRepository candidateRepository,
			VoteRepository voteRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
		this.voteRepository = voteRepository;
	}
	
	// Method to cast a vote
	@Override
	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		if(!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter with ID " + voterId + " does not exist.");
		}
		if(!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate with ID " + candidateId + " does not exist.");
		}
		
		Voter voter = voterRepository.findById(voterId).get();
		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter with ID " + voterId + " has already voted.");
		}
		// Proceed to cast the vote
		Candidate candidate = candidateRepository.findById(candidateId).get();
		Vote vote = new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
		voteRepository.save(vote);
		// Increment the candidate's vote count
		candidate.setVoteCount(candidate.getVoteCount() + 1);
		candidateRepository.save(candidate);
		// Mark the voter as having voted
		voter.setHasVoted(true);
		voterRepository.save(voter);
		return vote;
	}
	
	// Method to retrieve all votes
	@Override
	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}
}
