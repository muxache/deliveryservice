package com.mtuci.delivery.api.deliveryservice.controller;

import java.util.List;

import com.mtuci.delivery.api.deliveryservice.exception.ResourceNotFoundExeption;
import com.mtuci.delivery.api.deliveryservice.model.Product;
import com.mtuci.delivery.api.deliveryservice.repository.ProductRepository;

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
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @CrossOrigin
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/product")
    public ResponseEntity<?> listProduct() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return productRepository.findById(id).map(deliver -> {
            return new ResponseEntity<>(productRepository.getOne(id), HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @CrossOrigin
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.status(HttpStatus.OK).build();
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @CrossOrigin
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productRepository.findById(id).map(p -> {
            if (product.getBrand() != null) {
                p.setBrand(product.getBrand());
            }
            if (product.getType() != null) {
                p.setType(product.getType());
            }
            return new ResponseEntity<>(productRepository.save(p), HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }


}
