package com.codingchallenge.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Enrollee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrolleeId;
	
	@NotNull
	private String name;
	@NotNull
	private boolean activationStatus;
	@NotNull
	private String birthDate;
	
	private String phoneNumber;

	public Enrollee() {
		super();
	}

	public Enrollee(String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public Enrollee(int enrolleeId, String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.enrolleeId = enrolleeId;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public int getEnrolleeId() {
		return enrolleeId;
	}

	public void setEnrolleeId(int enrolleeId) {
		this.enrolleeId = enrolleeId;
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
		return "Enrollee [enrolleeId=" + enrolleeId + ", name=" + name + ", activationStatus=" + activationStatus
				+ ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + "]";
	}


	
}


