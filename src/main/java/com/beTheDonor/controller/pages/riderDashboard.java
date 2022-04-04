package com.beTheDonor.controller.pages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class riderDashboard
{
    @RequestMapping("/riderDashboard")
    public String getRiderPage()
    {
        return "riderDashboard";
    }
}
