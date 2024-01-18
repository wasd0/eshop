package com.wasd.usermicroservice.persistence.repository;

import com.wasd.usermicroservice.persistence.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void save(User user);
    void update(User user);
    void delete(User user);
}
