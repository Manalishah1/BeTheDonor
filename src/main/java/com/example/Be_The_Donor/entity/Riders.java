package com.example.Be_The_Donor.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="riders")
@Builder
public class Riders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "driver_name", nullable = false)
	private String driverName;
	
	@Column(name = "age")
	private Long age;
	
	@Column(name = "delivery")
	private Boolean delivery;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public Riders() {
		super();
	}

	public Riders(Long id, String driverName, Long age, Boolean delivery) {
		super();
		this.id = id;
		this.driverName = driverName;
		this.age = age;
		this.delivery = delivery;
	}

}
