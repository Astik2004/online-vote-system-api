package com.aky.votezy.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Voter entity representing a voter in the election system.<br>
 * <b>Author : Er.Astik Yadav</b>
 */

@Entity
@Data
public class Voter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	// To track if the voter has already voted
	private boolean hasVoted=false;
	
	// One-to-one relationship with Vote
	@OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
	@JsonIgnore
	private Vote vote;
}
