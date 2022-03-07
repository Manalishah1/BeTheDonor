package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1" )
@AllArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping(path = "/getProducts")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
    }
}
