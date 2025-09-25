package com.aky.votezy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * ElectionResult entity representing the result of an election.</br>
 * <b>Author : Er.Astik Yadav</b>
 */
@Entity
@Data
public class ElectionResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Election name is mandatory")
	private String electionName;
	
	private int totalVotes;
	
	@OneToOne
	@JoinColumn(name = "winner_id")
	private Candidate winner;
}
