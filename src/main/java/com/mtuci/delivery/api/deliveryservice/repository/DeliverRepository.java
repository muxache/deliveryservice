package com.mtuci.delivery.api.deliveryservice.repository;

import com.mtuci.delivery.api.deliveryservice.model.Deliver;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliverRepository extends JpaRepository<Deliver, Long> {

}
