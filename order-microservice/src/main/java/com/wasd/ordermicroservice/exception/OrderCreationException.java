package com.wasd.ordermicroservice.exception;

public class OrderCreationException extends RuntimeException {
    public OrderCreationException() {
        super("Exception while order creating");
    }
}
