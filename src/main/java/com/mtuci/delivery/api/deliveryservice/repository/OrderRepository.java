package com.mtuci.delivery.api.deliveryservice.repository;

import com.mtuci.delivery.api.deliveryservice.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE deliver_id IS NULL AND timestamp = (SELECT MIN(timestamp) FROM orders WHERE deliver_id IS NULL) LIMIT 1",  nativeQuery = true)
    Order findOldestOrder();
    
}
