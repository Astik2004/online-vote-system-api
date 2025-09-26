package com.aky.votezy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aky.votezy.repository.CandidateRepository;
import com.aky.votezy.repository.VoteRepository;
import com.aky.votezy.repository.VoterRepository;

@Service
public class VotingService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	private VoteRepository voteRepository;
	@Autowired
	public VotingService(VoterRepository voterRepository, CandidateRepository candidateRepository,
			VoteRepository voteRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
		this.voteRepository = voteRepository;
	}
}
