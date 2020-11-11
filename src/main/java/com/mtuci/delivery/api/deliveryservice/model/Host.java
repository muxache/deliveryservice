package com.mtuci.delivery.api.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
public class Host {
    
    private String hostname;


    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}
