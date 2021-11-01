package com.nitc.tnpapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitc.tnpapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

}
