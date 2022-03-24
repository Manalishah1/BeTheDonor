package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface riderRepository extends JpaRepository<Orders,Long>
{

}
