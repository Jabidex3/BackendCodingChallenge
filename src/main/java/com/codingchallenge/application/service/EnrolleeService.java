package com.codingchallenge.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingchallenge.application.model.Enrollee;

@Service
public interface EnrolleeService {
	public void addEnrollee(Enrollee e);
	public List<Enrollee> findAll();
	public Enrollee getEnrolleeById(int eid);
	public void delete(int eid);
}
