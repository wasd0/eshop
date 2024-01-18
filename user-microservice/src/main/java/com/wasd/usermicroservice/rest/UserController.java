package com.wasd.usermicroservice.rest;

import com.wasd.usermicroservice.data.user.UserRequest;
import com.wasd.usermicroservice.data.user.UserResponse;
import com.wasd.usermicroservice.exception.UserAlreadyExistsException;
import com.wasd.usermicroservice.exception.UserNotFoundException;
import com.wasd.usermicroservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest request) throws UserAlreadyExistsException {
        return userService.save(request);
    }

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) throws UserNotFoundException, UserAlreadyExistsException {
        return userService.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }
}
