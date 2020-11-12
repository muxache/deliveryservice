package com.mtuci.delivery.api.deliveryservice.controller;

import com.mtuci.delivery.api.deliveryservice.model.Deliver;
import com.mtuci.delivery.api.deliveryservice.service.DeliverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeliverController {
    

    @Autowired
    private DeliverService deliverService;

    @CrossOrigin
    @PostMapping("/deliver")
    public ResponseEntity<?> createDeliver(@RequestBody Deliver deliver) {
        return new ResponseEntity<>(deliverService.createDeliver(deliver), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/deliver")
    public ResponseEntity<?> listDeliver() {
        return new ResponseEntity<>(deliverService.findAllDeliver(), HttpStatus.OK);
        
    }


    @CrossOrigin
    @GetMapping("/deliver/{id}")
    public ResponseEntity<?> getDeliverById(@PathVariable Long id) {
        return deliverService.getDeliverById(id).map(deliver -> {
            return new ResponseEntity<>(deliver, HttpStatus.OK);
        }).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    
    @CrossOrigin
    @DeleteMapping("/deliver/{id}")
    public ResponseEntity<?> deleteDeliver(@PathVariable Long id) {
        if (deliverService.deleteDeliverById(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    

}
