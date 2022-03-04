package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override

    public List<Product> getProducts() {
        List<Product> product = productRepository.findAll();
        return product;
    }
}
