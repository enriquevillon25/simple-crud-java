package com.test1.services;

import com.test1.entity.Product;
import com.test1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(UUID id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(UUID id, Product product) {
        Product findProduct = productRepository.findById(id).get();
        findProduct.setPrice(product.getPrice());
        findProduct.setName(product.getName());
        productRepository.save(findProduct);
    }

    public void pathProduct(UUID id, Map<String, Object> updates) {
        productRepository.findById(id).map(product -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        product.setName((String) value);
                        break;
                    case "price":
                        product.setPrice(Double.valueOf(value.toString()));
                        break;
                }
            });
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Product> getPriceTop(Double price) {
        return productRepository.findByPriceGreaterThan(price);
    }
}
