package com.wasd.ordermicroservice.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Long productId) {
        super(String.format("Order with id '%d' not found", productId));
    }
}
