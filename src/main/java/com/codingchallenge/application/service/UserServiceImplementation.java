package com.codingchallenge.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.User;
import com.codingchallenge.application.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void addUser(User u) {
		userRepo.save(u);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int uid) {
		List<User> allUsers = userRepo.findAll();
		for(int i=0;i<allUsers.size();i++) {
			if(allUsers.get(i).getUserId()==uid) {
				return allUsers.get(i);
			}
		}
		return null;
		
	}

	@Override
	public void delete(int uid) {
		userRepo.deleteById(uid);
	}

}
