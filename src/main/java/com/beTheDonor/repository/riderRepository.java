package com.beTheDonor.repository;

import com.beTheDonor.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface riderRepository extends JpaRepository<Orders,Long>
{

}
