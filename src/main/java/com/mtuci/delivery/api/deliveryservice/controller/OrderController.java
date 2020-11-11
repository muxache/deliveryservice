package com.mtuci.delivery.api.deliveryservice.controller;

import java.sql.Timestamp;
import java.time.Instant;

import com.mtuci.delivery.api.deliveryservice.model.Order;
import com.mtuci.delivery.api.deliveryservice.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class OrderController {
    
    @Autowired
    private OrderRepository orderRepository;

    @CrossOrigin
    @PostMapping("/order/new")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        order.setTimestamp(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/order")
    public ResponseEntity<?> listOrder() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return orderRepository.findById(id).map(deliver -> {
            return new ResponseEntity<>(orderRepository.getOne(id), HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @CrossOrigin
    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderRepository.findById(id).map(o -> {
            if (order.getAddress() != null) {
                o.setAddress(order.getAddress());
            }
            if (order.getDeliverId() != null) {
                o.setDeliverId(order.getDeliverId());
            }
            if (order.getProductId() != null) {
                o.setProductId(order.getProductId());
            }
            return new ResponseEntity<>(orderRepository.save(o), HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @CrossOrigin
    @GetMapping("/order/get")
    public ResponseEntity<?> getOldestOrder() {
        Order o = orderRepository.findOldestOrder();
        if (o != null) {
            return new ResponseEntity<>(orderRepository.findOldestOrder(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @CrossOrigin
    @PutMapping("/order/take/{id}")
    public ResponseEntity<?> takeOldestOrder(@PathVariable Long id) {
        Order order = orderRepository.findOldestOrder();
        if (order != null) {
            order.setDeliverId(id);
            return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        
    }

    @CrossOrigin
    @PutMapping("/order/end/{id}")
    public ResponseEntity<?> endOrder(@PathVariable Long id) {
        return orderRepository.findById(id).map(order -> {
            order.setComplete(true);
            order.setDuration(compareTwoTimeStamps(Timestamp.from(Instant.now()), order.getTimestamp()));
            return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @CrossOrigin
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return orderRepository.findById(id).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.status(HttpStatus.OK).build();
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }


    public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime){
        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        //long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        //long diffHours = diff / (60 * 60 * 1000);
        //long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffMinutes;
    }
}
