package com.beTheDonor.controller;

import com.beTheDonor.entity.Product;
import com.beTheDonor.repository.OrderItemsRepository;
import com.beTheDonor.repository.UserRepository;
import com.beTheDonor.service.ProductService;
import com.beTheDonor.repository.OrderRepository;
import com.beTheDonor.repository.ProductRepository;
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
}
