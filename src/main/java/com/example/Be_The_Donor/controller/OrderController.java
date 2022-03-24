package com.example.Be_The_Donor.controller;


import com.example.Be_The_Donor.entity.OrderItem;
import com.example.Be_The_Donor.entity.OrderResponse;
import com.example.Be_The_Donor.entity.Orders;
import com.example.Be_The_Donor.exception.ErrorResponse;
import com.example.Be_The_Donor.repository.OrderItemsRepository;
import com.example.Be_The_Donor.repository.OrderRepository;
import com.example.Be_The_Donor.repository.UserRepository;
import com.example.Be_The_Donor.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController
{
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    ErrorResponse er = new ErrorResponse();

    @PostMapping(value = "/api/v1/order")
    public ErrorResponse addOrder(@RequestBody JSONObject payload) throws Exception {
        Boolean response = orderService.addOrder(payload);
        if(response == true) {
            er.setCode(200);
            er.setMessage("Order Added Successfully.");
            return er;
        }
        else {
            er.setCode(500);
            er.setMessage("Failed to add order. Try after sometime.");
            return er;
        }
    }

    @GetMapping(value = "/api/v1/getOrders")
    public List<OrderResponse> getOrders() throws Exception {
        List<Orders> orders = orderService.getOrders();
        List<List<OrderItem>> orderItems = new ArrayList<>();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderResponse orderResponse = new OrderResponse();
            List<OrderItem> orderItem = orderService.getOrderItems(orders.get(i));
            List<String> productName = new ArrayList<>();
            List<Double> price = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();

            orderResponse = new OrderResponse();
            orderResponse.setOrderId(orders.get(i).getOrderId());
            orderResponse.setTotalAmount(orders.get(i).getTotal());
            for (int j = 0; j < orderItem.size(); j++) {
                productName.add(orderItem.get(j).getProductId().getProductName());
                price.add(orderItem.get(j).getProductId().getPrice());
                quantity.add(orderItem.get(j).getQuantity());
            }
            orderResponse.setProductName(productName);
            orderResponse.setQuantity(quantity);
            orderResponse.setPrice(price);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
}

