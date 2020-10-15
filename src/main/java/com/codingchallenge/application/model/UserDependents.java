package com.codingchallenge.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDependents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userDependentsId;
	private int userId;
	private int dependentId;
	
	public UserDependents() {
		super();
	}

	public UserDependents(int userDependentsId, int userId, int dependentId) {
		super();
		this.userDependentsId = userDependentsId;
		this.userId = userId;
		this.dependentId = dependentId;
	}

	public int getUserDependentsId() {
		return userDependentsId;
	}

	public void setUserDependentsId(int userDependentsId) {
		this.userDependentsId = userDependentsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDependentId() {
		return dependentId;
	}

	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}

	@Override
	public String toString() {
		return "UserDependents [userDependentsId=" + userDependentsId + ", userId=" + userId + ", dependentId="
				+ dependentId + "]";
	}
	
}
