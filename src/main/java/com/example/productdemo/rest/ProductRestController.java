package com.example.productdemo.rest;

import com.example.productdemo.dto.PersistableProduct;
import com.example.productdemo.dto.ReadableProduct;
import com.example.productdemo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ReadableProduct> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/byEndDate")
    public List<ReadableProduct> getAllProductsByEndDatePassedOrNot(@RequestParam Boolean endDatePassed) {
        return productService.getAllProductsByEndDatePassedOrNot(endDatePassed);
    }

    @GetMapping("/{productId}")
    public ReadableProduct findProductById(@PathVariable int productId) {
        return productService.findProductById(productId);
    }

    @PostMapping
    public void saveProduct(@RequestBody PersistableProduct persistableProduct) {

        persistableProduct.setProductId(0);

        productService.saveProduct(persistableProduct);
    }

    @PutMapping
    public void updateProduct(@RequestBody PersistableProduct persistableProduct) {

        productService.saveProduct(persistableProduct);
    }

    @DeleteMapping("/{productId}")
    public String deleteProductById(@PathVariable int productId) {

        productService.deleteProductById(productId);

        return "Deleted product id - " + productId;
    }
}