package com.nitc.tnpapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitc.tnpapp.entity.PlacementOfficer;
import com.nitc.tnpapp.entity.Role;
import com.nitc.tnpapp.entity.User;
import com.nitc.tnpapp.repository.PlacementOfficerRepo;
import com.nitc.tnpapp.repository.RoleRepo;
import com.nitc.tnpapp.repository.StudentRepo;
import com.nitc.tnpapp.repository.UserRepo;
import com.nitc.tnpapp.service.HomeService;
import com.nitc.tnpapp.util.ResponseEntity;

@Service
public class HomeServiceImpl implements HomeService {

//	@Autowired
//	ResponseEntity ;

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	ResponseEntity responseEntity;

	@Autowired
	UserRepo userRepo;

	@Autowired
	PlacementOfficerRepo placementOfficerRepo;

	@Autowired
	StudentRepo studentRepo;
//	@Autowired
//	User user;

//	@Autowired
//	Role PO;

	@Override
	public ResponseEntity registerPO(PlacementOfficer placementOfficer) {

		Optional<PlacementOfficer> registeredPO = null;
		registeredPO = placementOfficerRepo.findById(placementOfficer.getPlacementOfficerId());
		Optional<PlacementOfficer> registeredPOEmail = null;
		registeredPOEmail = placementOfficerRepo
				.findByPlacementOfficerEmail(placementOfficer.getPlacementOfficerEmail());
		Optional<PlacementOfficer> registeredPOPhoneNo = null;
		registeredPOPhoneNo = placementOfficerRepo
				.findByPlacementOfficerPhoneNo(placementOfficer.getPlacementOfficerPhoneNo());

		if (registeredPO.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("A placement officer already present with the given id");
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
		}
		if (registeredPOEmail.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("A placement officer with the given email id already exists");
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
			return responseEntity;
		}
		if (registeredPOPhoneNo.isPresent()) {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("A placement officer with the given phone number already exists");
			responseEntity.setStatus(500);
			responseEntity.setObject(null);
			return responseEntity;
		}

//		///////////////////
		placementOfficerRepo.save(placementOfficer);
		User user = new User();
		user.setUsername(placementOfficer.getPlacementOfficerId());
		user.setPassword(placementOfficer.getPlacementOfficerId());
		user.setRole(roleRepo.findById("placement officer").get());
		userRepo.save(user);
		responseEntity.setHttpResponse("success");
		responseEntity.setMessage("Registration successfull");
		responseEntity.setStatus(200);
		responseEntity.setObject(placementOfficer);

		return responseEntity;
	}

	@Override
	public ResponseEntity login(User userLogin) {

		Optional<User> fetchedUser = userRepo.findById(userLogin.getUsername());

		if (fetchedUser.isPresent()) {
//			if (userLogin.getPassword() != fetchedUser.get().getPassword()) {
			if (!userLogin.getPassword().equals(fetchedUser.get().getPassword())) {
				System.out.println(userLogin.getPassword());
				System.out.println(fetchedUser.get().getPassword());
				responseEntity.setHttpResponse("failure");
				responseEntity.setMessage("Passwords dont match");
				responseEntity.setStatus(500);
				responseEntity.setObject(null);
			} else if (!userLogin.getRole().getRoleName().equals(fetchedUser.get().getRole().getRoleName())) {
				responseEntity.setHttpResponse("failure");
				responseEntity.setMessage("Roles dont match");
				responseEntity.setStatus(500);
				responseEntity.setObject(null);
			} else {
				responseEntity.setHttpResponse("success");
				
				responseEntity.setStatus(200);
				if (userLogin.getRole().getRoleName().equals("placement officer") ) {
					responseEntity.setMessage("successfully logged in as " + placementOfficerRepo.findById(userLogin.getUsername()).get().getPlacementOfficerName());
					responseEntity.setObject(placementOfficerRepo.findById(userLogin.getUsername()).get());
				} else if (userLogin.getRole().getRoleName().equals("student")) {
					responseEntity.setMessage("successfully logged in as " + studentRepo.findById(userLogin.getUsername()).get().getStudentName());
					responseEntity.setObject(studentRepo.findById(userLogin.getUsername()).get());
				}

			}

		} else {
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("User does not exist");
			responseEntity.setStatus(404);
			responseEntity.setObject(null);
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity addRole(Role role) {
		Optional<Role> savedRole = null;
		savedRole = roleRepo.findById(role.getRoleName());
		if (savedRole.isEmpty()) {
			roleRepo.save(role);
			responseEntity.setHttpResponse("success");
			responseEntity.setMessage("successfully inserted new role");
			responseEntity.setStatus(200);
			responseEntity.setObject(role);
		} else {

			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Role already present");
			responseEntity.setStatus(500);
			responseEntity.setObject(role);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity deleteRole(Role role) {
		Optional<Role> savedRole = null;
		savedRole = roleRepo.findById(role.getRoleName());
		if (savedRole.isEmpty()) {
//			roleRepo.save(role);
			responseEntity.setHttpResponse("failure");
			responseEntity.setMessage("Role does not exist");
			responseEntity.setStatus(500);
			responseEntity.setObject(role);
		} else {
			roleRepo.deleteById(role.getRoleName());
			responseEntity.setHttpResponse("success");
			responseEntity.setMessage("Successfully deleted the role");
			responseEntity.setStatus(200);
			responseEntity.setObject(role);
		}
		return responseEntity;
	}
}
