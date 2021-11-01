package com.nitc.tnpapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitc.tnpapp.entity.Company;
import com.nitc.tnpapp.entity.Role;
import com.nitc.tnpapp.entity.Student;
import com.nitc.tnpapp.entity.User;
import com.nitc.tnpapp.repository.CompanyRepo;
import com.nitc.tnpapp.repository.RoleRepo;
import com.nitc.tnpapp.repository.StudentRepo;
import com.nitc.tnpapp.repository.UserRepo;
import com.nitc.tnpapp.service.StudentService;
import com.nitc.tnpapp.util.ResponseEntity;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	ResponseEntity responseEntity;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	RoleRepo roleRepo;

	@Override
	public ResponseEntity registerStudent(Student student) {

		Optional<Student> registeredStudent = studentRepo.findById(student.getStudentRollNo());
		Optional<Student> registeredStudentEmail = studentRepo.findByEmail(student.getEmail());
		Optional<Student> registeredStudentPhoneNo = studentRepo.findByPhoneNo(student.getPhoneNo());

		if (registeredStudent.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Student with the given roll no already exists");
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
			return responseEntity;
		}

		if (registeredStudentEmail.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Student with the given email id already exists");
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
			return responseEntity;
		}
		if (registeredStudentPhoneNo.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Student with the given phone number already exists");
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
			return responseEntity;
		}

		student.setPlacedCompany(null);
		student.setPlacementStatus("Not Placed");
		student = studentRepo.save(student);

		User registeredUser = new User();
		registeredUser.setPassword(student.getStudentRollNo());
		registeredUser.setUsername(student.getStudentRollNo());
		registeredUser.setRole(roleRepo.findById("student").get());

		userRepo.save(registeredUser);
		responseEntity.setHttpResponse("success");
		responseEntity.setMessage("Registration successfull");
		responseEntity.setStatus(200);
		responseEntity.setObject(student);
		return responseEntity;
	}

	public ResponseEntity updateStudentProfile(Student student) {

		Optional<Student> fetchedStudent = null;
		fetchedStudent = studentRepo.findById(student.getStudentRollNo());

		if (fetchedStudent.isPresent()) {
			fetchedStudent.get().setStudentRollNo(student.getStudentRollNo());
			fetchedStudent.get().setStudentName(student.getStudentName());
			fetchedStudent.get().setCgpa(student.getCgpa());
			fetchedStudent.get().setEmail(student.getEmail());
			fetchedStudent.get().setPhoneNo(student.getPhoneNo());

			studentRepo.save(fetchedStudent.get());
			responseEntity.setHttpResponse("Success");
			responseEntity.setMessage("Successfully updated your profile ");
			responseEntity.setStatus(200);
			responseEntity.setObject(fetchedStudent.get());
		} else {
			responseEntity.setHttpResponse("Error");
			responseEntity.setMessage("Something went wrong");
			responseEntity.setStatus(500);
			responseEntity.setObject(fetchedStudent.get());
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity getStudentByRollNo(String studentRollNo) {
		Optional<Student> fetchedStudent = null;
		fetchedStudent = studentRepo.findById(studentRollNo);
		if (fetchedStudent.isPresent()) {
			responseEntity.setHttpResponse("Success");
			responseEntity.setMessage("Successfully fetched the student details");
			responseEntity.setStatus(200);
			responseEntity.setObject(fetchedStudent.get());
		} else {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Student not found");
			responseEntity.setStatus(404);
			responseEntity.setObject(null);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity companiesActiveToApply() {

		List<Company> activeCompanies = companyRepo.findByIsActive(true);
		if (activeCompanies.size() == 0) {
//			System.out.println("service if");
			responseEntity.setHttpResponse("success");
			responseEntity.setMessage("No active companies found");
			responseEntity.setStatus(200);
			responseEntity.setObject(activeCompanies);
		} else {
//			System.out.println("service else");
			responseEntity.setHttpResponse("success");
			responseEntity.setMessage("Active companies found");
			responseEntity.setStatus(200);
			responseEntity.setObject(activeCompanies);
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity applyForCompany(String companyCode, String studentRollNo) {

		Optional<Student> fetchedStudent = null;
		fetchedStudent = studentRepo.findById(studentRollNo);

		Optional<Company> fetchedCompany = null;
		fetchedCompany = companyRepo.findById(companyCode);

		if (fetchedStudent.isEmpty()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("No student found with the given roll no");
			responseEntity.setStatus(404);
			responseEntity.setObject(null);
			return responseEntity;
		}
		if (fetchedCompany.isEmpty()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("No company found with the given company code");
			responseEntity.setStatus(404);
			responseEntity.setObject(null);
			return responseEntity;
		}

		if (!fetchedStudent.get().getCompaniesApplied().contains(fetchedCompany.get())) {

			fetchedStudent.get().getCompaniesApplied().add(fetchedCompany.get());
			studentRepo.save(fetchedStudent.get());
			responseEntity.setHttpResponse("Success");
			responseEntity.setMessage("Successfully Applied for " + fetchedCompany.get().getCompanyName());
			responseEntity.setStatus(200);
			responseEntity.setObject(fetchedStudent);
			return responseEntity;
		} else {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Student has already applied for " + fetchedCompany.get().getCompanyName());
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
			return responseEntity;
		}
	}

}
