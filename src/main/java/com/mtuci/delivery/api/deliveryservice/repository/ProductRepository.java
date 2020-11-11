package com.mtuci.delivery.api.deliveryservice.repository;

import com.mtuci.delivery.api.deliveryservice.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
