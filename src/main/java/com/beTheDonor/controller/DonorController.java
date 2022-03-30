package com.beTheDonor.controller;

import com.beTheDonor.repository.OrderItemsRepository;
import com.beTheDonor.repository.OrderRepository;
import com.beTheDonor.repository.ProductRepository;
import com.beTheDonor.repository.UserRepository;
import com.beTheDonor.config.PasswordEncoder;
import com.beTheDonor.service.ApplicationUserService;
import com.beTheDonor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class DonorController {
    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    private DaoAuthenticationProvider authenticationManager;
    @Autowired
    private ApplicationUserService userDetailsService;

    @GetMapping("/api/v1/donorview")
    public String donorLogin(Model model) {
        return "donorView";
    }

}
