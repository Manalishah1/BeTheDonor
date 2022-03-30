package com.beTheDonor.repository;

import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByUserId(ApplicationUser user);
}
