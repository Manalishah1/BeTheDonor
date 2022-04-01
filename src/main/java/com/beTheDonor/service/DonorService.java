package com.beTheDonor.service;

import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.entity.Orders;
import com.beTheDonor.repository.OrderRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DonorService {
    @Autowired
    OrderRepository orderRepository;


    public Boolean changeStatusOfOrder(JSONObject payload) {
        ArrayList<Long> jsonAddress = (ArrayList) payload.get("orderId");
        for (int i = 0; i < jsonAddress.size(); i++) {
            Orders order;
            Long id;
            id = ((Number) jsonAddress.get(i)).longValue();
            order = orderRepository.getById(id);
            order.setOrderStatus("pending delivery");
            orderRepository.save(order);

        }

        return null;
    }
}
