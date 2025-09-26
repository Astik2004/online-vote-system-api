package com.aky.votezy.service;

import java.util.List;

import com.aky.votezy.entity.Voter;

public interface VoterService {
    
    Voter registerVoter(Voter voter);

    List<Voter> getAllVoters();

    Voter getVoterById(Long id);

    Voter updateVoter(Long id, Voter updatedVoter);

    void deleteVoter(Long id);
}
