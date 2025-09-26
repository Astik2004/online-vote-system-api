package com.aky.votezy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * VoteResponseDTO represents the data transfer object for the response after casting a vote, including a message, success status, voter ID, and candidate ID.<br>
 * <b>Author : Er.Astik Yadav</b>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDTO {
	private String message;
	private Boolean success;
	private Long voterId;
	private Long candidateId;
}
