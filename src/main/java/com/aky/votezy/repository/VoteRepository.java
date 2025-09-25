package com.aky.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aky.votezy.entity.Vote;
/**
 * Repository interface for Vote entity.</br>
 * Provides CRUD operations and custom query methods.</br>
 * <b>Author : Er.Astik Yadav</b>
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
