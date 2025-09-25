package com.aky.votezy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * Vote entity representing a vote cast by a voter for a candidate.
 * <b>Author : Er.Astik Yadav</b>
 */
@Entity
@Data
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// One-to-one relationship with Voter
	@OneToOne
	@JoinColumn(name = "candidate_id", unique = true)
	private Voter voter;
	
	// Many-to-one relationship with Candidate
	@ManyToOne
	@JoinColumn(name = "voter_id")
	private Candidate candidate;
	

}
