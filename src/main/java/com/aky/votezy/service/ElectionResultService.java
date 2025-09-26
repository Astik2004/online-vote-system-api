package com.aky.votezy.service;
import java.util.List;
import com.aky.votezy.entity.ElectionResult;
/**
 * Service interface for managing election results.
 * <b>Author : Er.Astik Yadav</b>
 */

public interface ElectionResultService {
	/**
	 * Declares the result of an election based on the election name.
	 * 
	 * @param electionName the name of the election
	 * @return the ElectionResult object containing the results
	 */
	public ElectionResult declareElectionResult(String electionName);
	public List<ElectionResult> getAllElectionResults();

}
