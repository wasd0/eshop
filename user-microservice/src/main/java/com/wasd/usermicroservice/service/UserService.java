package com.wasd.usermicroservice.service;

import com.wasd.usermicroservice.data.user.UserRequest;
import com.wasd.usermicroservice.data.user.UserResponse;
import com.wasd.usermicroservice.exception.UserAlreadyExistsException;
import com.wasd.usermicroservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findById(Long id) throws UserNotFoundException;
    UserResponse save(UserRequest request) throws UserAlreadyExistsException;
    UserResponse update(Long id, UserRequest request) throws UserNotFoundException, UserAlreadyExistsException;
    void delete(Long id) throws UserNotFoundException;
}
