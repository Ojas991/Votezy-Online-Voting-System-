package com.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.entity.Voter;
import com.votezy.exception.DuplicateResourceException;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VoterService {
		private VoterRepository voterRepository;
		
		private CandidateRepository candidateRepository;
		
		@Autowired
		public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
			
			this.voterRepository = voterRepository;
			this.candidateRepository = candidateRepository;
		}
		
		//Register an Voter-->
		public Voter registerVoter(Voter voter){
			
			if(voterRepository.existsByEmail(voter.getEmail())) {
				throw new DuplicateResourceException("Voter with Email id : " + voter.getEmail() + " already exists");
			}
			return voterRepository.save(voter);
		}
		
		//Get All Voters-->
		public List<Voter> getAllVoter(){
			return voterRepository.findAll();
		}
		
		//Get Voters by Id-->
		public Voter getVoterById(Long id) {
			Voter voter = voterRepository.findById(id).orElse(null);
			
			if(voter == null) {
				throw new ResourceNotFoundException("Voter with id " + id + "Not Found");
			}
			
			return voter;
		}
		
		//Update a Voter-->
		public Voter updateVoter(Long id, Voter updatedVoter) {
			Voter voter = voterRepository.findById(id).orElse(null);
			
			if(voter == null) {
				throw new ResourceNotFoundException("Voter with id " + id + "Not Found");
			}
			if(updatedVoter.getName() != null) {
				voter.setName(updatedVoter.getName());
			}
			
			if(updatedVoter.getEmail() !=null) {
				voter.setEmail(updatedVoter.getEmail());
			}
			
			return voterRepository.save(voter);
		}
		
		//Delete a Voter-->
		@Transactional
		public void deleteVoter(Long id) {
			Voter voter = voterRepository.findById(id).orElse(null);
			
			if(voter == null) {
				throw new ResourceNotFoundException("Cann't Delete voter with id " + id + " as it does't exist");
			}
			
			Vote vote = voter.getVote();
			if(vote != null) {
				Candidate candidate = vote.getCandidate();
				candidate.setVoteCount(candidate.getVoteCount()-1);
				candidateRepository.save(candidate);
			}
			
			voterRepository.delete(voter);
		}
}
