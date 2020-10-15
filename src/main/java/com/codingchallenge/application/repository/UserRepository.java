package com.codingchallenge.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenge.application.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
