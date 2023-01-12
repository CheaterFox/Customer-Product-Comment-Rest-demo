package com.example.productdemo.service;

import com.example.productdemo.dto.PersistableProduct;
import com.example.productdemo.dto.ReadableProduct;
import com.example.productdemo.entity.Product;

import java.util.List;

public interface ProductService {
    List<ReadableProduct> getAllProducts();
    List<ReadableProduct> getAllProductsByEndDatePassedOrNot(Boolean endDatePassed);

    ReadableProduct findProductById(int productId);

    Product findProductByIdForPopulator(int productId);

    void saveProduct(PersistableProduct persistableProduct);

    void deleteProductById(int productId);

}