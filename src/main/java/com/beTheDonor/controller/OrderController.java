package com.beTheDonor.controller;


import com.beTheDonor.repository.OrderItemsRepository;
import com.beTheDonor.repository.OrderRepository;
import com.beTheDonor.repository.UserRepository;
import com.beTheDonor.entity.PatientOrdersResponse;
import com.beTheDonor.exception.ErrorResponse;
import com.beTheDonor.service.CreditAmountService;
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

    @Autowired
    CreditAmountService creditAmountService;

    ErrorResponse er = new ErrorResponse();

    @PostMapping(value = "/api/v1/patient/order")
    public ErrorResponse addOrder(@RequestBody JSONObject payload) throws Exception {
        Boolean response = orderService.addOrder(payload);
        if(response == true) {
            creditAmountService.orderFromCredits();
            er.setCode(200);
            er.setMessage("Order Added Successfully.");
        }
        else {
            er.setCode(500);
            er.setMessage("Failed to add order. Try after sometime.");
        }
        return er;
    }

    @GetMapping(value = "/api/v1/patient/getOrders/{id}")
    @ResponseBody
    public List<PatientOrdersResponse> getOrdersByUserId(@PathVariable String id) throws Exception {
        Long userId = Long.valueOf(id);
        List<PatientOrdersResponse> orderResponses = orderService.getOrdersResponseByUserId(userId);
        return orderResponses;
    }

    @GetMapping(value = "/api/v1/donor/getOrders")
    public List<PatientOrdersResponse> getAllOrders() {
        List<PatientOrdersResponse> orderResponses = orderService.getOrderResponse();
        return orderResponses;
    }
}