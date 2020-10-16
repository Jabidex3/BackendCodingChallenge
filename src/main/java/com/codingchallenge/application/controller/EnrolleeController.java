package com.codingchallenge.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/enrollee")
public class EnrolleeController {

	@Autowired
	private EnrolleeService es;
	
	@Autowired
	private DependentService ds;
	
	//Get an enrollee [by enrolleeId]
	@GetMapping("/info/{enrolleeId}")
	public ResponseEntity<Enrollee> getEnrolleeDetails(@PathVariable int enrolleeId){
		List<Enrollee> allUsers = es.findAll();
		for(int i=0;i<allUsers.size();i++) {
			if(allUsers.get(i).getEnrolleeId()==enrolleeId) {
				return new ResponseEntity<Enrollee>(allUsers.get(i),HttpStatus.ACCEPTED);
			}
		}
		//could not find enrollee with associated enrollee id
		return new ResponseEntity<Enrollee>(HttpStatus.NOT_FOUND);
		
	}
	
	//Add a new enrollee
	@PostMapping("/enroll")
	public ResponseEntity<Enrollee> enroll(@RequestBody Enrollee u){
		if(u.getName()!=null && u.getBirthDate()!=null && (u.isActivationStatus() || !u.isActivationStatus())) {
			Enrollee newUser = new Enrollee(u.getName(),u.isActivationStatus(),u.getBirthDate(),u.getPhoneNumber());
			es.addEnrollee(newUser);
			return new ResponseEntity<Enrollee>(newUser,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Enrollee>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//Modify an existing enrollee [by enrolleeId]
	@PutMapping("/update")
	public ResponseEntity<Enrollee> updateEnrollee(@RequestBody Enrollee u){
		Enrollee toBeUpdated = es.getEnrolleeById(u.getEnrolleeId());
		
		if(toBeUpdated!=null) {
			if(u.getName()!=null) {
				toBeUpdated.setName(u.getName());
			}
			
			toBeUpdated.setActivationStatus(u.isActivationStatus());
			
			if(u.getBirthDate()!=null) {
				toBeUpdated.setBirthDate(u.getBirthDate());
			}
			
			if(u.getPhoneNumber()!=null) {
				toBeUpdated.setPhoneNumber(u.getPhoneNumber());
			}
			
			es.addEnrollee(toBeUpdated);//update existing record in db
			return new ResponseEntity<Enrollee>(toBeUpdated,HttpStatus.ACCEPTED);
		}
		else {
			//could not find user with associated enrollee id, so update cant be performed
			return new ResponseEntity<Enrollee>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Delete an existing enrollee [by enrolleeId] 
	//Also deletes all dependents of the deleted enrollee
	@DeleteMapping("/delete")
	public ResponseEntity<Enrollee> deleteEnrollee(@RequestBody Enrollee u){
		Enrollee toBeDeleted = es.getEnrolleeById(u.getEnrolleeId());
		if(toBeDeleted!=null) {
			es.delete(toBeDeleted.getEnrolleeId());
			List<Dependent> allDependents = ds.findAll();
			List<Integer> depIds = new ArrayList<Integer>();
			for(int j=0;j<allDependents.size();j++) {
				if(allDependents.get(j).getEnrolleeId()==u.getEnrolleeId()) {
					depIds.add(allDependents.get(j).getDependentId());
				}
			}
			
			for(int k=0;k<depIds.size();k++) {
				ds.delete(depIds.get(k)); //deleting all dependents associated with the enrollee
			}
			
			return new ResponseEntity<Enrollee>(HttpStatus.ACCEPTED);
		}
		else {
			//could not find user with associated enrollee id
			return new ResponseEntity<Enrollee>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
