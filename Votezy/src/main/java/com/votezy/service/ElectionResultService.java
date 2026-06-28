package com.votezy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votezy.entity.Candidate;
import com.votezy.entity.ElectionResult;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.ElectionResultRepository;
import com.votezy.repository.VoteRepository;

@Service
public class ElectionResultService {
	private CandidateRepository candidateRepository;
	private ElectionResultRepository electionResultRepository;
	private VoteRepository voteRepository;
	
	@Autowired
	public ElectionResultService(CandidateRepository candidateRepository,
			ElectionResultRepository electionResultRepository, VoteRepository voteRepository) {
		
		this.candidateRepository = candidateRepository;
		this.electionResultRepository = electionResultRepository;
		this.voteRepository = voteRepository;
	}
	
	//Declare Election Result--->
	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult> existingResult = this.electionResultRepository.findByElectionName(electionName);
		
		if(existingResult.isPresent()) {
			return existingResult.get();
		}
		
		if(voteRepository.count() == 0) {
			throw new IllegalStateException("Cannot decalare the Result as No Votes have been casted.");
		}
		
		List<Candidate> allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		
		if(allCandidates.isEmpty()) {
			throw new ResourceNotFoundException("No Candidate Available.");
		}
		
		Candidate winner = allCandidates.get(0);
		
		int totalVotes = 0;
		for(Candidate candidate : allCandidates) {
			totalVotes += candidate.getVoteCount();
		}
		
		ElectionResult result = new ElectionResult();
		
		result.setElectionName(electionName);
		
		result.setWinner(winner);
		
		result.setTotalVotes(totalVotes);
		
		return electionResultRepository.save(result);
	}
	
	//Get Election Result--->
	public List<ElectionResult> getAllResults(){
		return electionResultRepository.findAll();
	}
}
