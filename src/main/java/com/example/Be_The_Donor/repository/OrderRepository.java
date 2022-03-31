package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.entity.Orders;
import com.example.Be_The_Donor.model.PatientRiderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>
{

    List<Orders> findByUserId(ApplicationUser user);

    @Query("select distinct d.city from Orders o inner join DeliveryAddress d on d.addressId = o.deliveryAddressId.addressId")
    List<String>findCities();

    @Query(nativeQuery = true)
    List<PatientRiderModel> getByCityName(String cityName);

}

