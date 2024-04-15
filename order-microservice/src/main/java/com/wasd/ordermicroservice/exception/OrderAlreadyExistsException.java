package com.wasd.ordermicroservice.exception;

public class OrderAlreadyExistsException extends Exception {
    public OrderAlreadyExistsException(Long orderId) {
        super(String.format("Order with id '%d' already exists", orderId));
    }
}
