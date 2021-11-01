package com.nitc.tnpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nitc.tnpapp.entity.Student;
import com.nitc.tnpapp.service.StudentService;
import com.nitc.tnpapp.util.ResponseEntity;

@RestController
public class StudentController {
	
	@Autowired
	ResponseEntity responseEntity;
	
	@Autowired
	StudentService studentService;
	
	
	@PostMapping("/student/register")
	public ResponseEntity registerStudent(@RequestBody Student student) {
		
		responseEntity = studentService.registerStudent(student);
		
		return responseEntity;
	}
	
	@PostMapping("/student/updateprofile")
	public ResponseEntity updateStudentProfile(@RequestBody Student student) {
		
		responseEntity = studentService.updateStudentProfile(student);
		return responseEntity;
	}
	
	@GetMapping("/student/getstudent")
	public ResponseEntity getStudentDetails(@RequestParam String studentRollNo) {
		
		responseEntity = studentService.getStudentByRollNo(studentRollNo);
		return responseEntity;
	}
	
	@GetMapping("/student/activecompnies")
	public ResponseEntity companiesActiveToApply() {
//		System.out.println("controller");
		responseEntity = studentService.companiesActiveToApply();
		return responseEntity;
	}
	@PutMapping("/student/apply")
	public ResponseEntity applyForCompany(@RequestParam String companyCode, @RequestParam String studentRollNo) {
		responseEntity = studentService.applyForCompany(companyCode, studentRollNo);
		return responseEntity;
	}
	
}
