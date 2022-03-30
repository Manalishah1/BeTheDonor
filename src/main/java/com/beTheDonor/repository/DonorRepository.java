package com.beTheDonor.repository;

import java.util.List;

import com.beTheDonor.entity.Donors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// api for CRUD operations like create, read, update and delete
public interface DonorRepository extends JpaRepository<Donors, Long> {
	
	List<Donors> findAllByHelpDone(Boolean flag);
	List<Donors> findAll();
	
	@Query("SELECT SUM(m.amount) FROM Donors m where m.helpDone = true")
	Double selectTotals();

}
