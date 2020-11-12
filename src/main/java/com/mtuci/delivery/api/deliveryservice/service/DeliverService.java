package com.mtuci.delivery.api.deliveryservice.service;

import java.util.List;
import java.util.Optional;

import com.mtuci.delivery.api.deliveryservice.model.Deliver;
import com.mtuci.delivery.api.deliveryservice.repository.DeliverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeliverService {

    @Autowired
    private DeliverRepository deliverRepository;
    
    public List<Deliver> findAllDeliver() {
        return deliverRepository.findAll();
    }
    

    public Deliver createDeliver(Deliver deliver) {
        return deliverRepository.save(deliver);
    }


    public Optional<Deliver> getDeliverById(Long id) {
        return deliverRepository.findById(id);
    }

    public boolean deleteDeliverById(Long id) {
        return deliverRepository.findById(id).map(deliver -> {
            deliverRepository.delete(deliver);
            return true;
        }).orElse(false);
    }

}
