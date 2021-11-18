package com.nitc.tnpapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitc.tnpapp.entity.Company;
import com.nitc.tnpapp.entity.Student;
import com.nitc.tnpapp.repository.CompanyRepo;
import com.nitc.tnpapp.repository.StudentRepo;
import com.nitc.tnpapp.service.CompanyService;
import com.nitc.tnpapp.util.ResponseEntity;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	ResponseEntity responseEntity;

	@Autowired
	StudentRepo studentRepo;

	@Override
	public ResponseEntity registerCompany(Company company) {

		Optional<Company> registeredCompany = null;
		company.setActive(true);
		registeredCompany = companyRepo.findById(company.getCompanyCode());

		if (registeredCompany.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("A company with the given companyCode already exists");
			responseEntity.setObject(company);
			responseEntity.setStatus(500);
		} else {
			companyRepo.save(company);
			responseEntity.setHttpResponse("success");
			responseEntity.setMessage("succesfully registered the company");
			responseEntity.setObject(company);
			responseEntity.setStatus(200);

		}

		return responseEntity;
	}

	@Override
	public ResponseEntity updatePlacementStatus(String studentRollNo, boolean placementStatus,
			String placedCompanyCode) {

		Optional<Student> fetchedStudent = null;
		fetchedStudent = studentRepo.findById(studentRollNo);
		if (fetchedStudent.isPresent()) {
			if (fetchedStudent.get().getPlacementStatus().equalsIgnoreCase("Not Placed")) {
				if (placementStatus == true) {
					fetchedStudent.get().setPlacementStatus("Placed");
					fetchedStudent.get().setPlacedCompany(companyRepo.findById(placedCompanyCode).get());
					studentRepo.save(fetchedStudent.get());
					responseEntity.setHttpResponse("Success");
					responseEntity.setMessage("Successfully updated student's placement status ");
					responseEntity.setStatus(200);
				}
			} else {
				responseEntity.setHttpResponse("Failed");
				responseEntity.setMessage("Student is already placed");
				responseEntity.setStatus(500);
			}
		} else {
			responseEntity.setHttpResponse("Error");
			responseEntity.setMessage("Something went wrong");
			responseEntity.setStatus(500);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity closeCompany(String companyCode) {
		
		Optional<Company> fetchedCompany = null;
		fetchedCompany = companyRepo.findById(companyCode);
//		System.out.println(fetchedCompany.get());
		if (fetchedCompany.isPresent()) {
			fetchedCompany.get().setActive(false);
			companyRepo.save(fetchedCompany.get());
			responseEntity.setHttpResponse("success");
			responseEntity.setMessage("successfully deactivated the company");
			responseEntity.setStatus(200);
			responseEntity.setObject(fetchedCompany);
		} else {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Company not found");
			responseEntity.setStatus(404);
			responseEntity.setObject(null);
		}
		return responseEntity;
		
	}
}
