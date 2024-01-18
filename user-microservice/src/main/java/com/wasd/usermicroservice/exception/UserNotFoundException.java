package com.wasd.usermicroservice.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long userId) {
        super(String.format("User with id '%d' not found", userId));
    }
}
