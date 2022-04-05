package com.beTheDonor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientOrders {
    @RequestMapping(value = "/patient/orders")
    public String patientOrders() {
        return "patientOrders";
    }
}
