package com.beTheDonor.controller;


import com.beTheDonor.repository.OrderItemsRepository;
import com.beTheDonor.repository.OrderRepository;
import com.beTheDonor.repository.UserRepository;
import com.beTheDonor.entity.OrderResponse;
import com.beTheDonor.exception.ErrorResponse;
import com.beTheDonor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

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

    @GetMapping(value = "/api/v1/getOrders/{id}")
    @ResponseBody
    public List<OrderResponse> getOrdersByUserId(@PathVariable String id) throws Exception {
        Long userId = Long.valueOf(id);
        List<OrderResponse> orderResponses = orderService.getOrdersResponseByUserId(userId);
        return orderResponses;
    }

    @GetMapping(value = "/api/v1/getOrders")
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getOrderResponse();
        return orderResponses;
    }
}