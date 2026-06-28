package com.votezy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.entity.Voter;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.exception.VoteNotAllowedException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.VoteRepository;
import com.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VotingService {
	
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	private VoterRepository voterRepository;
	
	public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {
		
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}
	
	//Cast Vote
	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		if(! voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not Found with Id : " + voterId);
		}
		
		if(! candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate not Found with Id : " + candidateId);
		}
		
		Voter voter = voterRepository.findById(voterId).get(); 
		
		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter Id : " + voterId + " has already casted Vote");
		}
		
		Candidate candidate = candidateRepository.findById(candidateId).get();
		
		Vote vote = new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
		//voteRepository.save(vote);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		voter.setVote(vote);
		voter.setHasVoted(true);
		voterRepository.save(voter);
		
		return vote;
	}
	
	//Get All Votes-->
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}
}
