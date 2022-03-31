package com.beTheDonor.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class deliveryAddress {
    @RequestMapping(value = "/patient/deliveryAddress")
    public String deliveryAddress() {
        return "deliveryAddress";
    }
}
