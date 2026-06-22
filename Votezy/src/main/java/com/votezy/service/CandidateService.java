package com.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;

@Service
public class CandidateService {
	
	private CandidateRepository candidateRepository;

	@Autowired
	public CandidateService(CandidateRepository candidateRepository) {
		
		this.candidateRepository = candidateRepository;
	}
	
	//Add Candidate--->
	public Candidate addCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	//Get All Candidate--->
	public List<Candidate> getAllCandidadate(){
		return candidateRepository.findAll();
	} 
	
	//Get Candidate By Id--->
	public Candidate getCandidateById(Long id) {
		Candidate candidate = candidateRepository.findById(id).orElse(null);
		
		if(candidate == null) {
			throw new ResourceNotFoundException("Candidate with Id : " + id + " not found");
		}
		
		return candidate;
	}
	
	//Update Candidate--->
	public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
		Candidate candidate = getCandidateById(id);
		
		if(updatedCandidate.getName() != null) {
			candidate.setName(updatedCandidate.getName());
		}
		
		if(updatedCandidate.getParty() != null) {
			candidate.setParty(updatedCandidate.getParty());
		}
		
		return candidateRepository.save(candidate);
	}
	
	//Delete Candidate
	public void deleteCandidate(Long id) {
		Candidate candidate = getCandidateById(id);
		
		List<Vote> votes= candidate.getVote();
		
		for(Vote v : votes) {
			v.setCandidate(null);;
		}
		
		candidate.getVote().clear();
		
		candidateRepository.delete(candidate);
	}
}
 