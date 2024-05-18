package com.wasd.productmicroservice.exception.common;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(Class<?> target) {
        super(String.format("%s already exists", target.getSimpleName()));
    }
}
