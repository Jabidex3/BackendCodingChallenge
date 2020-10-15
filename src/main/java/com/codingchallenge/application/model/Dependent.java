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
	//add in userId to simplify thingss
	
	public Dependent() {
		super();
	}

	public Dependent(int dependentId, String name, String birthDate) {
		super();
		this.dependentId = dependentId;
		this.name = name;
		this.birthDate = birthDate;
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

	@Override
	public String toString() {
		return "Dependent [dependentId=" + dependentId + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}


