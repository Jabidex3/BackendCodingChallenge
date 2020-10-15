package com.codingchallenge.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.Dependent;

@Service
public interface DependentService {
	public void addDependent(Dependent d);
	public List<Dependent> findAll();
	public List<Dependent> findAllByUser(int userId);
	public void delete(int dependentId);
}
