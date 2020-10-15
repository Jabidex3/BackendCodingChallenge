package com.codingchallenge.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.Dependent;
import com.codingchallenge.application.model.UserDependents;
import com.codingchallenge.application.repository.DependentRepository;
import com.codingchallenge.application.repository.UserDependentsRepository;

@Service
public class DependentServiceImplementation implements DependentService{

	@Autowired
	private DependentRepository dependentRepo;
	
	@Autowired
	private UserDependentsRepository udRepo;
	
	@Override
	public void addDependent(Dependent d) {
		dependentRepo.save(d);
	}

	@Override
	public List<Dependent> findAll() {
		return dependentRepo.findAll();
	}

	@Override
	public List<Integer> findAllByUser(int userId) {
		List<UserDependents> mappings = udRepo.findAll();
		List<Integer> dependentIdsOfGivenUserId = new ArrayList<Integer>();
		for(int i=0;i<mappings.size();i++) {
			if(mappings.get(i).getUserId()==userId) {
				dependentIdsOfGivenUserId.add(mappings.get(i).getDependentId());
			}
		}
		
		return dependentIdsOfGivenUserId;
	}

}
