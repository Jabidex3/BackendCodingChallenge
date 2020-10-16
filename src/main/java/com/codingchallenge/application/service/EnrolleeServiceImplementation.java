package com.codingchallenge.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.Enrollee;
import com.codingchallenge.application.repository.EnrolleeRepository;

@Service
public class EnrolleeServiceImplementation implements EnrolleeService {

	@Autowired
	private EnrolleeRepository enrolleeRepo;
	
	@Override
	public void addEnrollee(Enrollee e) {
		enrolleeRepo.save(e);
	}

	@Override
	public List<Enrollee> findAll() {
		return enrolleeRepo.findAll();
	}

	@Override
	public Enrollee getEnrolleeById(int eid) {
		List<Enrollee> allUsers = enrolleeRepo.findAll();
		for(int i=0;i<allUsers.size();i++) {
			if(allUsers.get(i).getEnrolleeId()==eid) {
				return allUsers.get(i);
			}
		}
		return null;
		
	}

	@Override
	public void delete(int eid) {
		enrolleeRepo.deleteById(eid);
	}

}
