package com.aky.votezy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aky.votezy.entity.ElectionResult;
/**
 * Repository interface for ElectionResult entity.</br>
 * Provides CRUD operations and custom query methods.</br>
 * <b>Author : Er.Astik Yadav</b>
 */

@Repository
public interface ElectionRepository extends JpaRepository<ElectionResult, Long> {
	Optional<ElectionRepository> findByElectionName(String electionName);
	
}
