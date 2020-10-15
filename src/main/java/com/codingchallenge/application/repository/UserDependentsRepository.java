package com.codingchallenge.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenge.application.model.UserDependents;

public interface UserDependentsRepository extends JpaRepository<UserDependents,Integer> {

}
