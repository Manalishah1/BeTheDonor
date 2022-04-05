package com.beTheDonor.service.impl;


import com.beTheDonor.entity.Orders;
import com.beTheDonor.model.PatientRiderModel;
import com.beTheDonor.model.ReadyToDeliverModel;
import com.beTheDonor.repository.OrderRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderService {

    @Autowired
    OrderRepository orderRepository;


    public List<String> getCities() {
        return orderRepository.findCities();
    }

    public List<PatientRiderModel> getByCityName(String cityName) {
        return orderRepository.getByCityName(cityName);
    }

    public Boolean changeStatusOfOrder(JSONObject payload) {
        ArrayList<Long> jsonAddress = (ArrayList) payload.get("orderId");
        for (int i = 0; i < jsonAddress.size(); i++) {
            Orders order;
            Long id;
            id = ((Number) jsonAddress.get(i)).longValue();
            order = orderRepository.getById(id);
            order.setOrderStatus("Ready To Deliver");
            orderRepository.save(order);
        }
        return true;

    }

    public List<ReadyToDeliverModel> getRemaining()
    {
        return orderRepository.getPendingOrders();
    }

    public Double getTips(List<Long> ids)
    {
        return orderRepository.getTips(ids);
    }

    public void getOrderById(Long orderId)
    {
        Orders order = orderRepository.getById(orderId);
        order.setOrderStatus("pending delivery");
        orderRepository.save(order);
    }
}
