package com.wasd.ordermicroservice.exception.common;

public class NotFoundException extends Exception {

    public NotFoundException(Class<?> target) {
        super(String.format("%s not found", target.getSimpleName()));
    }
}
