package com.beTheDonor.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="donors")
@Builder
public class Donors {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "donor_name", nullable = false)
	private String donorName;
	
	@Column(name = "age")
	private Long age;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "help_done")
	private Boolean helpDone;
	

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Boolean getHelpDone() {
		return helpDone;
	}

	public void setHelpDone(Boolean helpDone) {
		this.helpDone = helpDone;
	}

	public Donors() {
		super();
	}

	public Donors(Long id, String donorName, Long age, Double amount, Boolean helpDone) {
		super();
		this.id = id;
		this.donorName = donorName;
		this.age = age;
		this.amount = amount;
		this.helpDone = helpDone;
	}



}
