package com.example.Be_The_Donor.repository;


import com.example.Be_The_Donor.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<ApplicationUser,Long>
{
    Optional<ApplicationUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE ApplicationUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableApplicationUser(String email);


    @Query("select a from ApplicationUser a " +
            "WHERE a.applicationUserRole = 'Patient'")
    List<ApplicationUser> getPatient();

    @Query("select a from ApplicationUser a " +
            "WHERE a.applicationUserRole = 'Rider'")
    List<ApplicationUser> getRider();

    @Query("select a from ApplicationUser a " +
            "WHERE a.applicationUserRole = 'Donor'")
    List<ApplicationUser> getDonor();

    @Override
    List<ApplicationUser> findAll();
}
