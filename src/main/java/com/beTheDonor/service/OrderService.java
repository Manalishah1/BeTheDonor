package com.beTheDonor.service;

import com.beTheDonor.entity.OrderItem;
import com.beTheDonor.entity.PatientOrdersResponse;
import com.beTheDonor.entity.Orders;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Boolean addOrder(JSONObject payload);
    List<OrderItem> getOrderItems(Orders orders);
    List<Orders> getOrdersByUserId(Long userId);
    List<PatientOrdersResponse> getOrdersResponseByUserId(Long userId);
    List<Orders> getAllOrders();
    List<PatientOrdersResponse> getOrderResponse();
}