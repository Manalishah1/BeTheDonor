package com.beTheDonor.repository;

import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.entity.Orders;
import com.beTheDonor.model.PatientRiderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>
{

    List<Orders> findByUserId(ApplicationUser user);
    List<Orders> findByOrderStatusAndTotalLessThanEqual(String orderStatus,Double total);

    @Query("select distinct d.city from Orders o inner join DeliveryAddress d on d.addressId = o.deliveryAddressId.addressId")
    List<String>findCities();

    @Query(nativeQuery = true)
    List<PatientRiderModel> getByCityName(String cityName);

}

