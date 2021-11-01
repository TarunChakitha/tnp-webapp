package com.nitc.tnpapp.service;

import com.nitc.tnpapp.entity.Company;
import com.nitc.tnpapp.util.ResponseEntity;


public interface CompanyService {
	public ResponseEntity registerCompany(Company company);
	public ResponseEntity updatePlacementStatus(String studentRollNo, boolean placementStatus, String placedCompanyCode);
	public ResponseEntity closeCompany(String companyCode);
}
