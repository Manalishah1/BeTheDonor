package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

