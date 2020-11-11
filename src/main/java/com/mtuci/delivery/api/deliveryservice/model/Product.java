package com.mtuci.delivery.api.deliveryservice.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "product_seq", sequenceName = "product_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
    private Long id;

    @Column
    private String brand;

    @Column
    private String type;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", brand='" + getBrand() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
    
    
}
