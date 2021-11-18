package com.nitc.tnpapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitc.tnpapp.entity.PlacementOfficer;

@Repository
public interface PlacementOfficerRepo extends JpaRepository<PlacementOfficer, String>{
	
	public Optional<PlacementOfficer> findByPlacementOfficerPhoneNo(long phoneNo);
	public Optional<PlacementOfficer> findByPlacementOfficerEmail(String email);

}
