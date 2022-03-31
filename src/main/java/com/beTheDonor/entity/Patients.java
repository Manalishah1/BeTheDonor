package com.beTheDonor.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patients")
@Builder
public class Patients {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "patient_name", nullable = false)
	private String patientName;
	
	@Column(name = "age")
	private Long age;
	
	@Column(name = "is_helped")
	private Boolean ishelped;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Boolean getIshelped() {
		return ishelped;
	}

	public void setIshelped(Boolean ishelped) {
		this.ishelped = ishelped;
	}

	public Patients() {
		super();
	}

	public Patients(Long id, String patientName, Long age, Boolean ishelped) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.age = age;
		this.ishelped = ishelped;
	}
	

}
