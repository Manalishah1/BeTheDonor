package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.OrderItem;
import com.example.Be_The_Donor.entity.Orders;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Boolean addOrder(JSONObject payload);
    List<Orders> getOrders();
    List<OrderItem> getOrderItems(Orders orders);
}
