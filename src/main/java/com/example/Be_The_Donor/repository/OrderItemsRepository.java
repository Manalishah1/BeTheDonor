package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.OrderItem;
import com.example.Be_The_Donor.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findByOrderId(Orders order);
}
