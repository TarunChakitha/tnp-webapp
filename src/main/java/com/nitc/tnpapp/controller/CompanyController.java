package com.nitc.tnpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitc.tnpapp.entity.Company;
import com.nitc.tnpapp.service.CompanyService;
import com.nitc.tnpapp.service.StudentService;
import com.nitc.tnpapp.util.ResponseEntity;

@RestController
public class CompanyController {

	@Autowired
	ResponseEntity responseEntity;

	@Autowired
	StudentService studentService;

	@Autowired
	CompanyService companyService;

	@PostMapping("/company/register")
	public ResponseEntity registerCompany(@RequestBody Company company) {
		
		responseEntity = companyService.registerCompany(company);
		return responseEntity;
	}

	@PutMapping("/company/updateplacementstatus")
	public ResponseEntity updatePlacementStatus(@RequestParam String studentRollNo, @RequestParam boolean placementStatus, @RequestParam String placedCompanyCode) {
		
		responseEntity = companyService.updatePlacementStatus(studentRollNo, placementStatus, placedCompanyCode);
		return responseEntity;
	}
	
	@PutMapping("/company/closecompany")
	public ResponseEntity closeCompany(@RequestParam String companyCode) {
		
		responseEntity = companyService.closeCompany(companyCode);
		return responseEntity;
	}
	
}
