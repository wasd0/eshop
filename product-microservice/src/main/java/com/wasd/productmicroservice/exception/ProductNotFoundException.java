package com.wasd.productmicroservice.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(Long productId) {
        super(String.format("Product with id '%d' not found", productId));
    }
}
