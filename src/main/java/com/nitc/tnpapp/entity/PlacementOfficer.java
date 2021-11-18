package com.nitc.tnpapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public @Data class PlacementOfficer {
	@Id
	private String placementOfficerId;
	
	private String placementOfficerName;
	
	@Column(unique = true)
	private long placementOfficerPhoneNo;
	
	@Column(unique = true)
	private String placementOfficerEmail;
	
}
