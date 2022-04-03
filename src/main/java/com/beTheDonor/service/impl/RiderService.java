package com.beTheDonor.service.impl;


import com.beTheDonor.model.PatientRiderModel;
import com.beTheDonor.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService
{
    @Autowired
    OrderRepository orderRepository;


    public List<String> getCities()
    {
       return orderRepository.findCities();
    }

    public List<PatientRiderModel>  getByCityName(String cityName)
    {
        return orderRepository.getByCityName(cityName);
    }
}
