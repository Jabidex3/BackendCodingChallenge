package com.codingchallenge.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenge.application.model.Dependent;

public interface DependentRepository extends JpaRepository<Dependent,Integer> {

}
