package com.nitc.tnpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitc.tnpapp.entity.PlacementOfficer;
import com.nitc.tnpapp.entity.Role;
import com.nitc.tnpapp.entity.User;
import com.nitc.tnpapp.service.HomeService;
import com.nitc.tnpapp.util.ResponseEntity;

@RestController
public class HomeController {

	@Autowired
	ResponseEntity responseEntity;

	@Autowired
	HomeService homeService;
	@RequestMapping("/")
	public String loadHomepage() {
		return "index";
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody User userLogin) {
		
		
		return responseEntity;
	}

	@PostMapping("/addrole")
	public ResponseEntity addRole(@RequestBody Role role) {
		
		responseEntity = homeService.addRole(role);
		return responseEntity;
	}
	
	@DeleteMapping("/deleterole")
	public ResponseEntity deleteRole(@RequestBody Role role) {
		responseEntity = homeService.deleteRole(role);
		return responseEntity;
	}
	
	@PostMapping("/po/register")
	public ResponseEntity registerPO(@RequestBody PlacementOfficer placementOfficer) {
		responseEntity = homeService.registerPO(placementOfficer);
		return responseEntity;
	}
}
