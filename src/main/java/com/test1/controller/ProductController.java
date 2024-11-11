package com.test1.controller;

import com.test1.entity.Product;
import com.test1.services.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @PatchMapping("/{id}")
    public void patchProduct(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        productService.pathProduct(id, updates);
    }

    @GetMapping("/price-top")
    public List<Product> getProductByTop(@RequestParam Double price) {
        return productService.getPriceTop(price);
    }
}
