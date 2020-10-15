package com.codingchallenge.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.application.model.Dependent;
import com.codingchallenge.application.model.User;
import com.codingchallenge.application.service.DependentService;

@RestController
@RequestMapping("/dependent")
public class DependentController {

	@Autowired
	private DependentService ds;
	
	@PostMapping("/add")
	public ResponseEntity<Dependent> addDependent(@RequestBody Dependent d) {
		if(d.getName() == null || d.getBirthDate()==null ||d.getUserId()==0) {
			return new ResponseEntity<Dependent>(HttpStatus.BAD_REQUEST);
		}
		else {
			Dependent toBeAdded = new Dependent();
			toBeAdded.setName(d.getName());
			toBeAdded.setBirthDate(d.getBirthDate());
			toBeAdded.setUserId(d.getUserId());
			ds.addDependent(toBeAdded);
			return new ResponseEntity<Dependent>(toBeAdded,HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<Dependent> removeDependent(@RequestBody Dependent d){
		List<Dependent> allDep = ds.findAll();
		for(int i=0;i< allDep.size();i++) {
			if(allDep.get(i).getDependentId()==d.getDependentId()) {
				ds.delete(allDep.get(i).getDependentId());
				return new ResponseEntity<Dependent>(HttpStatus.ACCEPTED);
			}
		}
		
		return new ResponseEntity<Dependent>(HttpStatus.NOT_FOUND);
	}
	
	
}
