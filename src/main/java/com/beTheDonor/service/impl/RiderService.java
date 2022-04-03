package com.beTheDonor.service.impl;


import com.beTheDonor.entity.Orders;
import com.beTheDonor.model.PatientRiderModel;
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

        }

        return true;
    }
}
