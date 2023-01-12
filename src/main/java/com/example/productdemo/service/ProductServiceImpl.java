package com.example.productdemo.service;

import com.example.productdemo.dao.ProductRepository;
import com.example.productdemo.dto.PersistableProduct;
import com.example.productdemo.dto.ReadableProduct;
import com.example.productdemo.entity.Product;
import com.example.productdemo.populator.ProductPopulator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductPopulator productPopulator;

    public ProductServiceImpl(ProductRepository productRepository, ProductPopulator productPopulator) {
        this.productRepository = productRepository;
        this.productPopulator = productPopulator;
    }

    @Override
    public List<ReadableProduct> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ReadableProduct> readableProductList =
                productPopulator.convertProductListToReadableProductList(productList);
        return readableProductList;
    }

    @Override
    public List<ReadableProduct> getAllProductsByEndDatePassedOrNot(Boolean endDatePassed) {
        if(endDatePassed) {
            List<Product> productListEndDatePassed = productRepository.findByEndDateBefore(LocalDate.now());
            List<ReadableProduct> readableProductListEndDatePassed =
                    productPopulator.convertProductListToReadableProductList(productListEndDatePassed);
            return readableProductListEndDatePassed;
        }
        else {
            List<Product> productListEndDateNotPassedOrNull =
                    productRepository.findByEndDateAfterOrEndDateIsNull(LocalDate.now());
            List<ReadableProduct> readableProductListEndDateNotPassedOrNull =
                    productPopulator.convertProductListToReadableProductList(productListEndDateNotPassedOrNull);
            return readableProductListEndDateNotPassedOrNull;
        }
    }

    @Override
    public ReadableProduct findProductById(int productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        ReadableProduct readableProduct = null;

        if (optionalProduct.isPresent()) {
            readableProduct = productPopulator.convertProductToReadableProduct(optionalProduct.get());

        } else {
            throw new RuntimeException("Did not find product id - " + productId);
        }
        return readableProduct;
    }

    // findProductByIdForPopulator this method is for dto converter class (CommentPopulator line: 31)
    @Override
    public Product findProductByIdForPopulator(int productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        Product product = null;

        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();

        } else {
            throw new RuntimeException("Did not find product id - " + productId);
        }
        return product;
    }

    @Override
    public void saveProduct(PersistableProduct persistableProduct) {
        Product product = productPopulator.convertPersistableProductToProduct(persistableProduct);
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(int productId) {
        Optional<Product> result = productRepository.findById(productId);

        if (result.isPresent()) {
            productRepository.deleteById(productId);
        }
        else {
            throw new RuntimeException("Did not find product id - " + productId);
        }
    }
}