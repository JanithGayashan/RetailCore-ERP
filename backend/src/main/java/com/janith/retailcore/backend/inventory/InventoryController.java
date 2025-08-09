package com.janith.retailcore.backend.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // DTO for the request body to make the API clearer
    static class StockUpdateRequest {
        public Long productId;
        public Long locationId;
        public int quantityChange;
    }

    @PostMapping("/stock")
    public ResponseEntity<?> updateStock(@RequestBody StockUpdateRequest request) {
        try {
            Inventory updatedInventory = inventoryService.updateStock(
                    request.productId,
                    request.locationId,
                    request.quantityChange
            );
            return ResponseEntity.ok(updatedInventory);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Handle errors gracefully
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}