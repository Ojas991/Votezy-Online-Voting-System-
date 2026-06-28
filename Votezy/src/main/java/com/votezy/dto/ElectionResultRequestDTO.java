package com.votezy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class ElectionResultRequestDTO {
	
	@NotBlank(message = "Election Name required")
	private String electionName;
	
	
}
