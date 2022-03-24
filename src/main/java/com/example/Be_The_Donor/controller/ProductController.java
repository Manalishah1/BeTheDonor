package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.repository.OrderItemsRepository;
import com.example.Be_The_Donor.repository.OrderRepository;
import com.example.Be_The_Donor.repository.ProductRepository;
import com.example.Be_The_Donor.repository.UserRepository;
import com.example.Be_The_Donor.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1" )
@ControllerAdvice
@AllArgsConstructor
public class ProductController {
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
    @GetMapping(path = "/getProducts")
    public ResponseEntity<List<Product>> getProducts(Model model){
        return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
    }

    @ModelAttribute
    public String addAttributes(Model model) {
        List<Product> product = productRepository.findAll();
        model.addAttribute("products", product);
        return "products";
    }
}
