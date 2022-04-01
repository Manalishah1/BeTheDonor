package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    ProductService productService;
    @GetMapping("/api/v1/admin")
    public String adminDashboard(Model model)
    {
        List<Product> products = productService.getProducts();
        model.addAttribute("products",products);

        return "admindashboard";
    }
}
