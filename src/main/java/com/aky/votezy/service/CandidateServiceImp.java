package com.aky.votezy.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aky.votezy.entity.Candidate;
import com.aky.votezy.entity.Vote;
import com.aky.votezy.exception.ResourceNotFoundException;
import com.aky.votezy.repository.CandidateRepository;

/**
 * CandidateServiceImp implements the CandidateService interface to provide business logic for managing candidates in the election system.<br>
 * It includes methods for adding, retrieving, updating, and deleting candidates.
 * <b>Author : Er.Astik Yadav</b>
 */
@Service
public class CandidateServiceImp implements CandidateService {
	
	private CandidateRepository candidateRepository;
	
	@Autowired
	public CandidateServiceImp(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	@Override
	public Candidate addCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}
	@Override
	public Candidate getCandidateById(Long id) {
		Candidate candidate= candidateRepository.findById(id).orElse(null);
		if(candidate == null) {
			throw new ResourceNotFoundException("Candidate with id " + id + " not found.");
		}
		return candidate;
	}
	@Override
	public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
		Candidate existingCandidate = candidateRepository.findById(id).orElse(null);
		if(existingCandidate == null) {
			throw new ResourceNotFoundException("Candidate with id " + id + " not found.");
		}
		if(updatedCandidate.getName()!=null)
		{
			existingCandidate.setName(updatedCandidate.getName());
		}
		if(updatedCandidate.getParty()!=null)
		{
			existingCandidate.setParty(updatedCandidate.getParty());
		}
		return candidateRepository.save(existingCandidate);
	}
	@Override
	public void deleteCandidate(Long id) {
		Candidate existingCandidate = candidateRepository.findById(id).orElse(null);
		if(existingCandidate == null) {
			throw new ResourceNotFoundException("Candidate with id " + id + " not found.");
		}
		List<Vote> votes = existingCandidate.getVotes();
		for(Vote vote : votes) {
			vote.setCandidate(null);
		}
		existingCandidate.getVotes().clear();
		candidateRepository.deleteById(id);
	}
}
