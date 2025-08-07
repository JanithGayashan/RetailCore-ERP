package com.janith.retailcore.backend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController // Marks this as a controller where every method returns a domain object instead of a view
@RequestMapping("/api/v1/products") // All endpoints in this class will start with this path
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired // Spring will automatically provide an instance of ProductRepository
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        // Use the repository to find all products in the database
        return productRepository.findAll();
    }
}