package com.janith.retailcore.backend.inventory;

import com.janith.retailcore.backend.location.Location;
import com.janith.retailcore.backend.location.LocationRepository;
import com.janith.retailcore.backend.product.Product;
import com.janith.retailcore.backend.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Important!

@Service // Marks this as a Spring service bean
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository, LocationRepository locationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    // The @Transactional annotation ensures this whole method runs in a single database transaction.
    // If any part fails, the whole operation is rolled back, ensuring data consistency.
    @Transactional
    public Inventory updateStock(Long productId, Long locationId, int quantityChange) {
        // Find the product and location, or throw an exception if they don't exist.
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + productId));
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Location ID: " + locationId));

        // Use our custom repository method to find the existing inventory record.
        Inventory inventory = inventoryRepository.findByProductAndLocation(product, location)
                .orElseGet(() -> {
                    // If no inventory record exists, create a new one.
                    Inventory newInventory = new Inventory();
                    newInventory.setProduct(product);
                    newInventory.setLocation(location);
                    newInventory.setQuantity(0);
                    return newInventory;
                });

        // Update the quantity.
        int newQuantity = inventory.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            // Prevent stock from going negative.
            throw new IllegalStateException("Not enough stock for product " + product.getName());
        }
        inventory.setQuantity(newQuantity);

        // Save the updated or new inventory record.
        return inventoryRepository.save(inventory);
    }
}