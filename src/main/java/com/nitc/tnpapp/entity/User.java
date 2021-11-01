package com.nitc.tnpapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public @Data class User {
	
	@Id
	private String username;
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;
	
	
	
}
