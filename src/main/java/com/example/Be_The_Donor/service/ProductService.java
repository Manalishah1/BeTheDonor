package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.Product;

import java.util.List;



public interface ProductService {
    Product addQuantity(Product product);
    void removeQuantity(Product product);
    List<Product> getProducts();

}
