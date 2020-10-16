package com.codingchallenge.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.application.model.Dependent;
import com.codingchallenge.application.model.Enrollee;
import com.codingchallenge.application.service.DependentService;
import com.codingchallenge.application.service.EnrolleeService;

@RestController
@RequestMapping("/dependent")
public class DependentController {

	@Autowired
	private DependentService ds;
	
	@Autowired
	private EnrolleeService es;
	
	//get a dependent [by dependentId]
	@GetMapping("/info/{dependentId}")
	public ResponseEntity<Dependent> getDependentDetails(@PathVariable int dependentId){
		List<Dependent> allDependents = ds.findAll();
		for(int i=0;i<allDependents.size();i++) {
			if(allDependents.get(i).getDependentId()==dependentId) {
				return new ResponseEntity<Dependent>(allDependents.get(i),HttpStatus.ACCEPTED);
			}
		}
		//could not find dependent with associated dependentId
		return new ResponseEntity<Dependent>(HttpStatus.NOT_FOUND);
		
	}
	
	//Add dependent to an existing enrollee
	@PostMapping("/add")
	public ResponseEntity<Dependent> addDependent(@RequestBody Dependent d) {
		if(d.getName() == null || d.getBirthDate()==null ||d.getEnrolleeId()==0) {
			return new ResponseEntity<Dependent>(HttpStatus.BAD_REQUEST);
		}
		else {
			boolean validEnrolleeIdProvided = false;
			List<Enrollee> allEnrollees = es.findAll();
			for(int i=0;i<allEnrollees.size();i++) {
				if(allEnrollees.get(i).getEnrolleeId()==d.getEnrolleeId()) {
					validEnrolleeIdProvided = true;
				}
			}
			
			if(validEnrolleeIdProvided) {
				Dependent toBeAdded = new Dependent();
				toBeAdded.setName(d.getName());
				toBeAdded.setBirthDate(d.getBirthDate());
				toBeAdded.setEnrolleeId(d.getEnrolleeId());
				ds.addDependent(toBeAdded);
				return new ResponseEntity<Dependent>(toBeAdded,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<Dependent>(HttpStatus.BAD_REQUEST);
			}
			
		}
	}
	
	//remove a dependent that is associated with an enrollee
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
	
	//modify existing dependent
	@PutMapping("/update")
	public ResponseEntity<Dependent> updateDependent(@RequestBody Dependent d){
		Dependent toBeUpdated = null;
		for(int i=0;i<ds.findAll().size();i++) {
			if(ds.findAll().get(i).getDependentId()==d.getDependentId()) {
				toBeUpdated = ds.findAll().get(i);
				break;
			}
		}
		
		if(toBeUpdated!=null) {
			if(d.getName()!=null) {
				toBeUpdated.setName(d.getName());
			}
			
			if(d.getBirthDate()!=null) {
				toBeUpdated.setBirthDate(d.getBirthDate());
			}
			
			if(d.getEnrolleeId() !=0 && d.getEnrolleeId() != toBeUpdated.getEnrolleeId()) {
				boolean validEnrolleeIdProvided = false;
				List<Enrollee> allEnrollees = es.findAll();
				for(int i=0;i<allEnrollees.size();i++) {
					if(allEnrollees.get(i).getEnrolleeId()==d.getEnrolleeId()) {
						validEnrolleeIdProvided = true;
					}
				}
				
				if(validEnrolleeIdProvided) {
					toBeUpdated.setEnrolleeId(d.getEnrolleeId());
				}
				else {
					return new ResponseEntity<Dependent>(HttpStatus.BAD_REQUEST);
				}
			}
			
			ds.addDependent(toBeUpdated); //update existing record in db
			return new ResponseEntity<Dependent>(toBeUpdated,HttpStatus.ACCEPTED);
		}
		else {
			//could not find user with associated enrollee id, so update cant be performed
			return new ResponseEntity<Dependent>(HttpStatus.NOT_FOUND);
		}
	}
}
