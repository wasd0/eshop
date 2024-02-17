package com.wasd.usermicroservice.service.impl;

import com.wasd.usermicroservice.data.user.UserRequest;
import com.wasd.usermicroservice.data.user.UserResponse;
import com.wasd.usermicroservice.exception.UserAlreadyExistsException;
import com.wasd.usermicroservice.exception.UserNotFoundException;
import com.wasd.usermicroservice.persistence.model.User;
import com.wasd.usermicroservice.persistence.repository.UserRepository;
import com.wasd.usermicroservice.service.SecurityService;
import com.wasd.usermicroservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(this::mapUserToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return mapUserToResponse(user);
    }

    @Override
    public UserResponse save(UserRequest request) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new UserAlreadyExistsException(request.username());
        }

        User user = createUserFromRequest(request);
        userRepository.save(user);

        return mapUserToResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) throws UserNotFoundException, UserAlreadyExistsException {
        User foundById =
                userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Optional<User> foundByUsername = userRepository.findByUsername(request.username());
        
        if (foundByUsername.isPresent() && !foundByUsername.get().getId().equals(id)) {
            throw new UserAlreadyExistsException(request.username());
        }
        
        foundById.setUsername(request.username());
        foundById.setEmail(request.email());
        foundById.setPassword(securityService.encodePassword(request.password()));

        userRepository.update(foundById);

        return mapUserToResponse(foundById);
    }

    @Override
    public void delete(Long id) throws UserNotFoundException {
        User userFromDb = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(userFromDb);
    }

    private User createUserFromRequest(UserRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(securityService.encodePassword(request.password()))
                .registeredAt(LocalDateTime.now())
                .build();
    }

    private UserResponse mapUserToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRegisteredAt());
    }
}
