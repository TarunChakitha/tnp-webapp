package com.nitc.tnpapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public @Data class Company {
	
	private String companyName;
	
	@Id
//	@OneToMany(cascade = CascadeType.ALL)
	private String companyCode;
	private float cgpaLimit;
	private String roleOffered;
	private boolean isActive;
	private long ctc;
	
	
//	company is mapped by companiesApplied with student entity 
	@ManyToMany(mappedBy = "companiesApplied",cascade = CascadeType.ALL)
	private List<Student> studentsApplied;
}



