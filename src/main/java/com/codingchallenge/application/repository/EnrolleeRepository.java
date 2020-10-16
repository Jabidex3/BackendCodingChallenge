package com.codingchallenge.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenge.application.model.Enrollee;

public interface EnrolleeRepository extends JpaRepository<Enrollee,Integer> {

}
