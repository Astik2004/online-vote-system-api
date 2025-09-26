package com.aky.votezy.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * VoteRequestDTO represents the data transfer object for casting a vote, containing voter and candidate IDs.<br>
 * <b>Author : Er.Astik Yadav</b>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteRequestDTO {
	@NotNull(message = "Voter ID is required")
	Long voterId;
	@NotNull(message = "Candidate ID is required")
	Long candidateId;
}
