package com.aky.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aky.votezy.entity.Candidate;
import com.aky.votezy.entity.Vote;
import com.aky.votezy.entity.Voter;
import com.aky.votezy.exception.DuplicateResourceException;
import com.aky.votezy.exception.ResourceNotFoundException;
import com.aky.votezy.repository.CandidateRepository;
import com.aky.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;
@Service
public class VoterServiceImp implements VoterService {
	
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	
	@Autowired
	public VoterServiceImp(VoterRepository voterRepository, CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}
	@Override
	public Voter registerVoter(Voter voter) {
		if(voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("Voter with email " + voter.getEmail() + " already exists.");
		}
		return voterRepository.save(voter);
	}
	@Override
	public List<Voter> getAllVoters() {
		return voterRepository.findAll();	
	}
	@Override
	public Voter getVoterById(Long id) {
		Voter voter= voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourceNotFoundException("Voter with id " + id + " not found.");
		}
		return voter;
	}
	@Override
	public Voter updateVoter(Long id, Voter updatedVoter) {
		Voter existingVoter = voterRepository.findById(id).orElse(null);
		if(existingVoter == null) {
			throw new ResourceNotFoundException("Voter with id " + id + " not found.");
		}
		existingVoter.setName(updatedVoter.getName());
		existingVoter.setEmail(updatedVoter.getEmail());
		return voterRepository.save(existingVoter);
	}
	@Override
	@Transactional
	public void deleteVoter(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourceNotFoundException("Voter with id " + id + " not found.");
		}
		Vote vote = voter.getVote();
		if(vote != null) {
			Candidate candidate = vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount() - 1);
			candidateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}
	
}