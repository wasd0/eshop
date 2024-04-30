package com.wasd.ordermicroservice.exception.common;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(Class<?> target) {
        super(String.format("%s already exists", target.getSimpleName()));
    }
}
