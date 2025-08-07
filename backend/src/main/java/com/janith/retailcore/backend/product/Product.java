package com.janith.retailcore.backend.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products") // This tells JPA to name the table "products"
@Getter // Lombok annotation to auto-generate getter methods
@Setter // Lombok annotation to auto-generate setter methods
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Let the database handle generating the ID
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String sku; // Stock Keeping Unit
}
