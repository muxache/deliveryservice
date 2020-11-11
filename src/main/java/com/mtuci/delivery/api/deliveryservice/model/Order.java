package com.mtuci.delivery.api.deliveryservice.model;

import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
public class Order {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "order_seq", sequenceName = "order_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq")
    private Long id;

    @Column
    private Long deliverId;

    @Column
    private Long productId;

    @Column
    private String address;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column
    private Timestamp timestamp;

    @Column
    private Long duration;

    @Column 
    private boolean complete;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public boolean isComplete() {
        return this.complete;
    }

    public boolean getComplete() {
        return this.complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }


    public Long getDeliverId() {
        return this.deliverId;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    


    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

   

    
}
