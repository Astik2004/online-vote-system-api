package com.aky.votezy.service;
import java.util.List;
import com.aky.votezy.entity.Vote;

/**
 * VotingService interface defines the contract for voting operations such as casting votes and retrieving vote records.<br>
 * <b>Author : Er.Astik Yadav</b>
 */
public interface VotingService {
	
	public Vote castVote(Long voterId, Long candidateId);
	public List<Vote> getAllVotes();

}
