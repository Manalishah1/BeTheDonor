package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByUserId(ApplicationUser user);
}
