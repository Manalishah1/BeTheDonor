package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Product getById(Long aLong);

    @Override
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Product> findAll();

    @Override
    <S extends Product> S save(S entity);
}

