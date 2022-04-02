package com.beTheDonor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatientOrderPlaced {
    @RequestMapping(value = "/patient/order-placed")
    public String patientOrders() {
        return "patientOrderSuccessPage";
    }
}
