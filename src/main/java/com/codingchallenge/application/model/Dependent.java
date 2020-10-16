package com.codingchallenge.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dependent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dependentId;
	
	private String name;
	private String birthDate;
	private int enrolleeId; //refers to provider's id
	
	public Dependent() {
		super();
	}


	public Dependent(int dependentId, String name, String birthDate, int enrolleeId) {
		super();
		this.dependentId = dependentId;
		this.name = name;
		this.birthDate = birthDate;
		this.enrolleeId = enrolleeId;
	}


	public int getDependentId() {
		return dependentId;
	}

	public void setDependentId(int dependentId) {
		this.dependentId = dependentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public int getEnrolleeId() {
		return enrolleeId;
	}


	public void setEnrolleeId(int enrolleeId) {
		this.enrolleeId = enrolleeId;
	}


	@Override
	public String toString() {
		return "Dependent [dependentId=" + dependentId + ", name=" + name + ", birthDate=" + birthDate + ", enrolleeId="
				+ enrolleeId + "]";
	}



}


