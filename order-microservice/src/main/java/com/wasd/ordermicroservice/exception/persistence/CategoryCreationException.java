package com.wasd.ordermicroservice.exception.persistence;

public class CategoryCreationException extends RuntimeException {
    public CategoryCreationException() {
        super("Wrong category data for creation!");
    }
}
