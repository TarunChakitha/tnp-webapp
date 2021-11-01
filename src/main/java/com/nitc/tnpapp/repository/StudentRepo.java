package com.nitc.tnpapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitc.tnpapp.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, String>{
	public Optional<Student> findByEmail(String email);
	public Optional<Student> findByPhoneNo(long phoneNo); 
}
