package com.beTheDonor.repository;

import com.beTheDonor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
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

