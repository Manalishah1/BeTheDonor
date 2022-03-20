package com.example.Be_The_Donor.repository;

import java.util.List;

import com.example.Be_The_Donor.entity.Riders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RiderRepository extends JpaRepository<Riders, Long> {
	List<Riders> findAllByDelivery(Boolean flag);
	List<Riders> findAll();
}
