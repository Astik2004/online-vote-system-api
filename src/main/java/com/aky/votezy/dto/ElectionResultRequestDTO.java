package com.aky.votezy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for requesting election results by election name.
 * <b>Author : Er.Astik Yadav</b>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResultRequestDTO {
	private String electionName;
}
