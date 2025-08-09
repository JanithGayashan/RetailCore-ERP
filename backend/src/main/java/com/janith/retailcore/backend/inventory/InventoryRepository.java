package com.janith.retailcore.backend.inventory;

import com.janith.retailcore.backend.location.Location;
import com.janith.retailcore.backend.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // This is a custom query method. Spring Data JPA will automatically
    // create the implementation for us based on the method name!
    // It allows us to easily find the stock for a specific product at a specific location.
    Optional<Inventory> findByProductAndLocation(Product product, Location location);
}