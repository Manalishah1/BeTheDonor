package com.example.Be_The_Donor.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderService
{

    static  ArrayList<String> cityList = new ArrayList<>() ;

    static
    {
        cityList.add("Halifax");
        cityList.add("Toronto");
        cityList.add("Haka");
    }

    public List<String> getCities()
    {
        return cityList;
    }
}
