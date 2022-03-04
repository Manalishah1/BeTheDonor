package com.example.Be_The_Donor.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class UserLoginController {
    @RequestMapping("/api/v1/login")
    public String login(){
        return "login";
    }

}
