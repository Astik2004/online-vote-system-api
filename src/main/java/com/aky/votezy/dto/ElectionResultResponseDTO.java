package com.aky.votezy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for responding with election results.
 * <b>Author : Er.Astik Yadav</b>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResultResponseDTO {
	private String electionName;
	private int totalVotes;
	private Long winnerId;
	private String winnerName;
	private int winnerVoteCount;
}
