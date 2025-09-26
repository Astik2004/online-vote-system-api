package com.aky.votezy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.core.util.Json;
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
	@JsonIgnore
	private Voter voter;
	
	// Many-to-one relationship with Candidate
	@ManyToOne
	@JoinColumn(name = "voter_id")
	@JsonIgnore
	private Candidate candidate;
	
	@JsonProperty("voterId")
	public long getVoterId() {
		return voter!=null?voter.getId():null;
	}
	@JsonProperty("candidateId")
	public long getCandidateId() {
		return candidate!=null?candidate.getId():null;
	}
}
