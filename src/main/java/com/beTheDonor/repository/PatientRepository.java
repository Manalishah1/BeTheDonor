package com.beTheDonor.repository;

import java.util.List;

import com.beTheDonor.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;


// jpa repository - api for CRUD operations
public interface PatientRepository extends JpaRepository<Patients, Long> {
	List<Patients> findAllByIshelped(Boolean flag);
	List<Patients> findAll();
}
