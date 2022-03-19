package com.example.Be_The_Donor.service.impl;

import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.exception.ResourceNotFoundException;
import com.example.Be_The_Donor.exception.StockNotPresentException;
import com.example.Be_The_Donor.repository.ProductRepository;
import com.example.Be_The_Donor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addQuantity(Product product) {
//		findById method retrieves the product by the Id
//		Return type is "Optional" return type
//		Optional- Container object which may or may not contain a non-null value.
//		If a value is present, isPresent returns true and returns the value.
        Optional<Product> optProduct = productRepository.findById(product.getProductId());
        Product dbProduct = null;
        if(optProduct.isPresent()) {
            dbProduct = optProduct.get();
//			Getting the product quantity from the database and adding this quantity to the new quantity specified by the PUT request
            dbProduct.setQuantity(dbProduct.getQuantity() + product.getQuantity());
//			Saving it in DB
            return productRepository.save(dbProduct);
        }else {
//			In case the Id is not found, then ResourceNotFoundException is thrown
            throw new ResourceNotFoundException("Product " + product.getProductName() +" not present");
        }

    }

    @Override
    public void removeQuantity(Product product) {
//		findById method retrieves the product by the Id
//		Return type is "Optional" return type
//		Optional- Container object which may or may not contain a non-null value.
//		If a value is present, isPresent returns true and get returns the value.
        Optional<Product> optProduct = productRepository.findById(product.getProductId());
        Product dbProduct = null;
        if (optProduct.isPresent()) {
            dbProduct = optProduct.get();
            if (dbProduct.getQuantity() > product.getQuantity()) {
//				Getting the product quantity from the database and subtracting this quantity to the new quantity specified by the DELETE request
                dbProduct.setQuantity(dbProduct.getQuantity() - product.getQuantity());
//				Saving it in DB
                productRepository.save(dbProduct);
            } else
                throw new StockNotPresentException("Stock not present :" + product.getQuantity());

        }else {
//			In case the Id is not found, then ResourceNotFoundException is thrown
            throw new ResourceNotFoundException("Product " + product.getProductName() + " not present");
        }
    }

    @Override
    public List<Product> getProducts() {
//		Returns all the products from the DB
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product){
        productRepository.save(product);
        return  product;
    }
}
