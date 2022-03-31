package com.beTheDonor.controller.pages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class riderDashboard
{
    @RequestMapping("/riderDashboard")
    public String getRiderPage()
    {
        return "riderDashboard";
    }

}
