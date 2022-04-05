package com.beTheDonor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientDashboard {
    @RequestMapping(value = "/patient/dashboard")
    public String patientDashboard() {
        return "patientDashboard";
    }
}
