package com.codingchallenge.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotNull
	private String name;
	@NotNull
	private boolean activationStatus;
	@NotNull
	private String birthDate;
	
	private String phoneNumber;

	public User() {
		super();
	}

	public User(String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}
	
	

	public User(int userId, String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.userId = userId;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", activationStatus=" + activationStatus + ", birthDate="
				+ birthDate + ", phoneNumber=" + phoneNumber + "]";
	}
	
}


