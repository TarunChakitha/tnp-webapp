package com.nitc.tnpapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.nitc.tnpapp.entity.PlacementOfficer;
import com.nitc.tnpapp.entity.Role;
import com.nitc.tnpapp.entity.User;
import com.nitc.tnpapp.util.ResponseEntity;

public interface HomeService {
	public ResponseEntity registerPO(PlacementOfficer placementOfficer);
	public ResponseEntity login(User userLogin);
	public ResponseEntity addRole(Role role);
	public ResponseEntity deleteRole(Role role);
}
