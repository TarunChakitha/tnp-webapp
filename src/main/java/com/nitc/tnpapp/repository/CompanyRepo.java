package com.nitc.tnpapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitc.tnpapp.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, String>{
	public List<Company> findByIsActive(boolean isActive);
}
