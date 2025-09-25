package com.aky.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aky.votezy.entity.Candidate;
/**
 * Repository interface for Candidate entity.</br>
 * Provides CRUD operations and custom query methods.</br>
 * <b>Author : Er.Astik Yadav</b>
 */

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	List<Candidate> findAllByOrderByVoteCountDesc();
}
