package com.mtuci.delivery.api.deliveryservice.controller;

import java.io.IOException;

import com.mtuci.delivery.api.deliveryservice.model.Host;
import com.mtuci.delivery.api.deliveryservice.service.HostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HostController {

    @Autowired
    private HostService hostService;

    @CrossOrigin
    @GetMapping("/hostname")
    public Host getHostName() {
        try {
            Host host = hostService.getHostName();
            return host;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // @CrossOrigin
    // @GetMapping("/deliver/{id}")
    // public ResponseEntity<?> getById(@PathVariable Long id) {
    //     return deliverRepository.findById(id).map(deliver -> {
    //         return new ResponseEntity<>(deliverRepository.getOne(id), HttpStatus.OK);
    //     }).orElseThrow(() -> new ResourceNotFoundExeption("Deliver not found with id " + id));
    // }
    
}
