package com.aky.votezy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDTO {
	private String message;
	private Boolean success;
	private Long voterId;
	private Long candidateId;
}
