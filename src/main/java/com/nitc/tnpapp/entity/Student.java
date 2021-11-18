package com.nitc.tnpapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public @Data class Student {

	private String studentName;

	@Id
	private String studentRollNo;
	
	@Column(unique = true)
	private long phoneNo;
	
	@Column(unique = true)
	private String email;
	
	private float cgpa;
	private String placementStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Company placedCompany;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Company> companiesApplied;
}













