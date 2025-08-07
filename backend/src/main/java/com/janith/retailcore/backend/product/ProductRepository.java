package com.janith.retailcore.backend.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Tells Spring this is a repository bean
public interface ProductRepository extends JpaRepository<Product, Long> {
    // <Product, Long> means "This repository works with the Product entity, and its ID is of type Long."
    // You can add custom query methods here later!
}
