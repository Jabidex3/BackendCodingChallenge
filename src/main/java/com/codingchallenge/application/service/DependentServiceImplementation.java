package com.codingchallenge.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.Dependent;
import com.codingchallenge.application.repository.DependentRepository;

@Service
public class DependentServiceImplementation implements DependentService{

	@Autowired
	private DependentRepository dependentRepo;
	
	@Override
	public void addDependent(Dependent d) {
		dependentRepo.save(d);
	}

	@Override
	public List<Dependent> findAll() {
		return dependentRepo.findAll();
	}

	@Override
	public List<Dependent> findAllByUser(int userId) {
		List<Dependent> allDependents = dependentRepo.findAll();
		List<Dependent> dependentIdsOfGivenUserId = new ArrayList<Dependent>();
		for(int i=0;i<allDependents.size();i++) {
			if(allDependents.get(i).getUserId()==userId) {
				dependentIdsOfGivenUserId.add(allDependents.get(i));
			}
		}
		
		return dependentIdsOfGivenUserId;
	}

	@Override
	public void delete(int dependentId) {
		dependentRepo.deleteById(dependentId);
	}

}
