package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addQuantity(Product product);
    void removeQuantity(Product product);
    List<Product> getProducts();
    Product addProduct(Product product);
}
