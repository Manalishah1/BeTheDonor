package com.example.Be_The_Donor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.service.ProductService;

import lombok.AllArgsConstructor;

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
    
    @PutMapping(path = "/updateProducts")
    public ResponseEntity<Product> addQuantity(@RequestBody Product product){
        return new ResponseEntity<Product>(productService.addQuantity(product), HttpStatus.OK);

    }

    @DeleteMapping(path = "/deleteProducts")
    public ResponseEntity<Void> deleteQuantity(@RequestBody Product product){
        productService.removeQuantity(product);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
