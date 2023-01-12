package com.example.productdemo.populator;


import com.example.productdemo.dto.PersistableProduct;
import com.example.productdemo.dto.ReadableProduct;
import com.example.productdemo.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

//Converting persistable model to domain model or domain model to readable model
@Component
public class ProductPopulator {

    public Product convertPersistableProductToProduct(PersistableProduct persistableProduct) {

        Product product = new Product();
        product.setId(persistableProduct.getProductId());
        product.setProductName(persistableProduct.getProductName());
        product.setPrice(persistableProduct.getPrice());
        product.setEndDate(persistableProduct.getEndDate());

        return product;
    }

    public ReadableProduct convertProductToReadableProduct(Product product) {

        ReadableProduct readableProduct = new ReadableProduct();
        readableProduct.setProductName(product.getProductName());
        readableProduct.setPrice(product.getPrice());
        readableProduct.setEndDate(product.getEndDate());

        return readableProduct;
    }

    public List<ReadableProduct> convertProductListToReadableProductList(List<Product> productList) {

        return productList.stream().map(this::convertProductToReadableProduct).toList();
    }
}