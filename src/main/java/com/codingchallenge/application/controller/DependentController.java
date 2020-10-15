package com.codingchallenge.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.application.service.DependentService;

@RestController
@RequestMapping("/user")
public class DependentController {

	@Autowired
	private DependentService ds;
	
	//take in a wrapper object
	//public void addDependent(@RequestBody User u,)
}
