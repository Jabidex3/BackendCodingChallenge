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
import com.codingchallenge.application.model.User;
import com.codingchallenge.application.service.DependentService;
import com.codingchallenge.application.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService us;
	
	@Autowired
	private DependentService ds;
	
	//Get a user [by userId]
	@GetMapping("/info/{userId}")
	public ResponseEntity<User> getUserDetails(@PathVariable int userId){
		List<User> allUsers = us.findAll();
		for(int i=0;i<allUsers.size();i++) {
			if(allUsers.get(i).getUserId()==userId) {
				return new ResponseEntity<User>(allUsers.get(i),HttpStatus.ACCEPTED);
			}
		}
		//could not find user with associated user id
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
	}
	
	//Add a new User
	@PostMapping("/enroll")
	public ResponseEntity<User> enroll(@RequestBody User u){
		if(u.getName()!=null && u.getBirthDate()!=null && (u.isActivationStatus() || !u.isActivationStatus())) {
			User newUser = new User(u.getName(),u.isActivationStatus(),u.getBirthDate(),u.getPhoneNumber());
			us.addUser(newUser);
			return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//Modify an existing User [by userId]
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User u){
		User toBeUpdated = us.getUserById(u.getUserId());
		
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
			
			us.addUser(toBeUpdated);//update existing record in db
			return new ResponseEntity<User>(toBeUpdated,HttpStatus.ACCEPTED);
		}
		else {
			//could not find user with associated user id, so update cant be performed
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Delete an existing User [by userId] 
	//Also deletes all dependents of the deleted user
	@DeleteMapping("/delete")
	public ResponseEntity<User> deleteUser(@RequestBody User u){
		User toBeDeleted = us.getUserById(u.getUserId());
		if(toBeDeleted!=null) {
			us.delete(toBeDeleted.getUserId());
			List<Dependent> allDependents = ds.findAll();
			List<Integer> depIds = new ArrayList<Integer>();
			for(int j=0;j<allDependents.size();j++) {
				if(allDependents.get(j).getUserId()==u.getUserId()) {
					depIds.add(allDependents.get(j).getDependentId());
				}
			}
			
			for(int k=0;k<depIds.size();k++) {
				ds.delete(depIds.get(k)); //deleting all dependents associated with the user
			}
			
			return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		}
		else {
			//could not find user with associated user id
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
