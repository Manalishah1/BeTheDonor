package com.beTheDonor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PatientOrderPlaced {
    @RequestMapping(value = "/patient/order-placed")
    public String patientOrders() {
        return "patientOrderSuccessPage";
    }
}
