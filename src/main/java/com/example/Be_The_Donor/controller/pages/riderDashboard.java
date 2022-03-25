package com.example.Be_The_Donor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class riderDashboard
{
    @RequestMapping("/api/v1/riderDashboard")
    public String getRiderPage()
    {
        return "riderDashboard";
    }
}
