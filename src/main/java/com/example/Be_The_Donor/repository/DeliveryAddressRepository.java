package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {

}
