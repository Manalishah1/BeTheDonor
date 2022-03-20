package com.example.Be_The_Donor.repository;

import java.util.List;

import com.example.Be_The_Donor.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;


// jpa repository - api for CRUD operations
public interface PatientRepository extends JpaRepository<Patients, Long> {
	List<Patients> findAllByIshelped(Boolean flag);
	List<Patients> findAll();
}
