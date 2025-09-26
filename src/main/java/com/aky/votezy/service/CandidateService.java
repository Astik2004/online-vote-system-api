package com.aky.votezy.service;
import java.util.List;
import com.aky.votezy.entity.Candidate;

/**
 * CandidateService interface defines the contract for managing candidates in the election system.<br>
 * It includes methods for adding, retrieving, updating, and deleting candidates.
 * <b>Author : Er.Astik Yadav</b>
 */

public interface CandidateService {
	public Candidate addCandidate(Candidate candidate);
	public Candidate getCandidateById(Long id);
	public Candidate updateCandidate(Long id, Candidate updatedCandidate);
	public void deleteCandidate(Long id);
	public List<Candidate> getAllCandidates();	
}
