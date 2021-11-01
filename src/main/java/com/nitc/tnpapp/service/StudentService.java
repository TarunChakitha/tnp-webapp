package com.nitc.tnpapp.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.nitc.tnpapp.entity.Student;
import com.nitc.tnpapp.util.ResponseEntity;

public interface StudentService {
	public ResponseEntity registerStudent(Student student) ;
	
	public ResponseEntity updateStudentProfile(Student student); 
	
	public ResponseEntity getStudentByRollNo(String studentRollNo);
	
	public ResponseEntity companiesActiveToApply();
	
	public ResponseEntity applyForCompany(String companyCode,String studentRollNo);
}
