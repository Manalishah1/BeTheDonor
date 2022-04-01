package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.service.AdminService;
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
    AdminService as;
    @GetMapping("/api/v1/admin")
    public String adminDashboard(Model model)
    {
        List<Product> products = productService.getProducts();
        model.addAttribute("products",products);

        List<ApplicationUser> users = as.getUsers();
        model.addAttribute("users", users);

        List<ApplicationUser> patients = as.getPatients();
        model.addAttribute("patients", patients);

        List<ApplicationUser> riders = as.getRiders();
        model.addAttribute("riders", riders);

        List<ApplicationUser> donors = as.getDonors();
        model.addAttribute("donors", donors);

        return "admindashboard";
    }
}
