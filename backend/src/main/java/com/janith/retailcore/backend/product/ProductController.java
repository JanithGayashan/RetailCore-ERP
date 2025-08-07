package com.janith.retailcore.backend.product; // Note: 'product' should be lowercase by convention

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Import all necessary annotations

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products") // Base path for all methods in this controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // === CREATE a new Product ===
    // This method handles HTTP POST requests to /api/v1/products
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // @RequestBody tells Spring to take the JSON sent by the client (Postman)
        // and automatically convert it into a Product Java object.
        // The save() method persists the new product to the database and returns
        // the saved entity, now including the auto-generated ID.
        return productRepository.save(product);
    }

    // === READ all Products ===
    // This method handles HTTP GET requests to /api/v1/products
    @GetMapping
    public List<Product> getAllProducts() {
        // This fetches all records from the 'products' table.
        return productRepository.findAll();
    }

    // === READ a single Product by its ID ===
    // This method handles HTTP GET requests to /api/v1/products/{id} (e.g., /api/v1/products/1)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // @PathVariable tells Spring to take the 'id' from the URL path
        // and use it as the value for the 'id' method parameter.
        Optional<Product> product = productRepository.findById(id);

        // This is a professional way to handle "not found" cases.
        // If the product exists, wrap it in a ResponseEntity with a 200 OK status.
        // If it does not exist, return an empty response with a 404 Not Found status.
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // === UPDATE an existing Product ===
    // This method handles HTTP PUT requests to /api/v1/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        // First, we try to find the existing product in the database.
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            // If it exists, we get the actual product object.
            Product existingProduct = optionalProduct.get();

            // We update its fields with the details from the request body.
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setSku(productDetails.getSku());

            // We save the modified product back to the database.
            // The save() method acts as an "update" if the entity already has an ID.
            final Product updatedProduct = productRepository.save(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        } else {
            // If the product with the given ID doesn't exist, return 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }

    // === DELETE a Product (Traditional, Clear-to-read version) ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // First, check if the product with the given ID exists in the database.
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            // If it exists, get the actual product object.
            Product productToDelete = optionalProduct.get();
            // Delete it from the database.
            productRepository.delete(productToDelete);
            // Return a successful response: 204 No Content.
            return ResponseEntity.noContent().build();
        } else {
            // If it does not exist, return a 404 Not Found response.
            return ResponseEntity.notFound().build();
        }
    }
}