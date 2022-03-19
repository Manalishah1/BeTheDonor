package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
    }

    @PutMapping()
//	ResponseEntity class<T> which extends HttpClass<T>
    public ResponseEntity<Product> addQuantity(@RequestBody Product product) {
//		adds the HTTPStatus code
//		Calling the addQuantity method of the interface ProductService
        return new ResponseEntity<Product>(productService.addQuantity(product), HttpStatus.OK);

    }

    @DeleteMapping()
//	ResponseEntity class<T> which extends HttpClass<T>
    public ResponseEntity<Void> deleteQuantity(@RequestBody Product product) {
//		adds the HTTPStatus code
//		Calling the removeQuantity method of the interface ProductService
        productService.removeQuantity(product);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping()
//	ResponseEntity class<T> which extends HttpClass<T>
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//		adds the HTTPStatus code
//		Calling the addQuantity method of the interface ProductService
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);

    }
}
