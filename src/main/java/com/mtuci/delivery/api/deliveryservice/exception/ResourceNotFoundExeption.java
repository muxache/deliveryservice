package com.mtuci.delivery.api.deliveryservice.exception;

public class ResourceNotFoundExeption extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundExeption(String message) {
        super(message);
    }
    public ResourceNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
    
}
