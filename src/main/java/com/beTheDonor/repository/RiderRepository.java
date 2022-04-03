package com.beTheDonor.repository;

import java.util.List;

import com.beTheDonor.entity.Riders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RiderRepository extends JpaRepository<Riders, Long> {
	List<Riders> findAllByDelivery(Boolean flag);
	List<Riders> findAll();
}
