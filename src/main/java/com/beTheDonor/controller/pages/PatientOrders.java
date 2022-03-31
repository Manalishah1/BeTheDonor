package com.beTheDonor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatientOrders {

    @RequestMapping(value = "/patientOrders")
    public String patientOrders() {
        return "patientOrders";
    }
}
