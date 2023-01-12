package com.example.productdemo.dao;

import com.example.productdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

   List<Product> findByEndDateBefore(LocalDate date);

    List<Product> findByEndDateAfterOrEndDateIsNull(LocalDate date);
}