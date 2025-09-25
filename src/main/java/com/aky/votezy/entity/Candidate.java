package com.aky.votezy.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * Candidate entity representing a candidate in the election system.</br>
 * <b>Author</b> :<b> Er.Astik Yadav</b>
 */
@Entity
@Data
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Party is mandatory")
	private String party;
	
	// To keep track of the number of votes received
	private int voteCount;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private List<Vote> votes;
}
