package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Long> {

}
