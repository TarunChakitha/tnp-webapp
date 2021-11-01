package com.nitc.tnpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nitc.tnpapp.util.ResponseEntity;

@Controller
public class HomeController {

	@Autowired
	ResponseEntity responseEntity;

	@RequestMapping("/")
	public String loadHomepage() {
		return "index";
	}

	

}
