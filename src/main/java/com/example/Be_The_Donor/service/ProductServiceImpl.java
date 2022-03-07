package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.exception.ResourceNotFoundException;
import com.example.Be_The_Donor.exception.StockNotPresentException;
import com.example.Be_The_Donor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override

    public List<Product> getProducts() {
        List<Product> product = productRepository.findAll();
        return product;
    }

    @Override
    public Product addQuantity(Product product) {
        Optional<Product> optProduct = productRepository.findById(product.getProductId());
        Product dbProduct = null;
        if(optProduct.isPresent()) {
            dbProduct = optProduct.get();
            dbProduct.setQuantity(dbProduct.getQuantity() + product.getQuantity());
            return productRepository.save(dbProduct);
        }else {
            throw new ResourceNotFoundException("Product " + product.getProductName() +" not present");
        }

    }

    @Override
    public void removeQuantity(Product product) {
        Optional<Product> optProduct = productRepository.findById(product.getProductId());
        Product dbProduct = null;
        if (optProduct.isPresent()) {
            dbProduct = optProduct.get();
            if (dbProduct.getQuantity() > product.getQuantity()) {
                dbProduct.setQuantity(dbProduct.getQuantity() - product.getQuantity());
                productRepository.save(dbProduct);
            } else
                throw new StockNotPresentException("Stock not present :" + product.getQuantity());

        }else {
            throw new ResourceNotFoundException("Product " + product.getProductName() +" not present");
        }


    }
}
