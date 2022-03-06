package com.example.Be_The_Donor.controller;


import com.example.Be_The_Donor.exception.ErrorResponse;
import com.example.Be_The_Donor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

@RestController
public class OrderController
{
    @Autowired
    OrderService orderService;

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
}

