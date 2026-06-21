package com.votezy.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Voter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is Required")
	private String name;
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Invalid mail Format")
	private String email;
	
	private boolean hasVoted = false;

    @OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
    @JsonIgnore
    private Vote vote;

}
