package com.wasd.productmicroservice.exception.common;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> target) {
        super(String.format("%s not found", target.getSimpleName()));
    }
}
