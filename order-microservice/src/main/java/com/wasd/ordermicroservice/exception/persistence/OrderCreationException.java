package com.wasd.ordermicroservice.exception.persistence;

public class OrderCreationException extends RuntimeException {
    public OrderCreationException() {
        super("Exception while order creating");
    }
}
