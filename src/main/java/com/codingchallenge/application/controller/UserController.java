package com.codingchallenge.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.application.model.User;
import com.codingchallenge.application.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService us;
	
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
			//could not find user with associated user id
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Delete an existing User [by userId]
	@DeleteMapping("/delete")
	public ResponseEntity<User> deleteUser(@RequestBody User u){
		User toBeDeleted = us.getUserById(u.getUserId());
		if(toBeDeleted!=null) {
			us.delete(toBeDeleted.getUserId());
			return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		}
		else {
			//could not find user with associated user id
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
