package com.codingchallenge.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.User;

@Service
public interface UserService {
	public void addUser(User u);
	public List<User> findAll();
	public User getUserById(int uid);
	public void delete(int uid);
}
