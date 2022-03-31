package com.beTheDonor.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {
    @GetMapping("/api/v1/admin")
    public String adminDashboard()
    {
        return "admindashboard";
    }
}
