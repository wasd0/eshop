package com.wasd.usermicroservice.service.impl;

import com.wasd.usermicroservice.data.user.UserRequest;
import com.wasd.usermicroservice.data.user.UserResponse;
import com.wasd.usermicroservice.exception.UserAlreadyExistsException;
import com.wasd.usermicroservice.exception.UserNotFoundException;
import com.wasd.usermicroservice.persistence.model.User;
import com.wasd.usermicroservice.persistence.repository.UserRepository;
import com.wasd.usermicroservice.service.SecurityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userJdbcRepository;
    @Mock
    SecurityService securityService;
    
    @Test
    void findAll_whenUsersExists_returnsUserResponseList() {
        when(userJdbcRepository.findAll()).thenReturn(List.of(new User(), new User()));
        List<UserResponse> result = userService.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }
    
    @Test
    void findAll_whenUsersNotExists_returnsEmptyList() {
        when(userJdbcRepository.findAll()).thenReturn(new ArrayList<>());
        List<UserResponse> result = userService.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }
    
    @Test
    void findById_whenCorrectId_returnsUserResponse() throws UserNotFoundException {
        Long userId = 1L;
        User user = User.builder().username("test").build();
        when(userJdbcRepository.findById(userId)).thenReturn(Optional.of(user));
        UserResponse result = userService.findById(userId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.username(), user.getUsername());
    }
    
    @Test
    void findById_whenIncorrectId_throwsUserNotFoundException() {
        Long userId = 1L;
        when(userJdbcRepository.findById(userId)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findById(userId));
    }
    
    @Test
    void save_whenUniqueUsername_saveNewUser() throws UserAlreadyExistsException {
        when(userJdbcRepository.findByUsername("test")).thenReturn(Optional.empty());
        when(securityService.encodePassword("")).thenReturn("123");
        UserRequest request = new UserRequest("test", "", "");
        UserResponse response = userService.save(request);
        verify(userJdbcRepository, times(1)).save(any());
        Assertions.assertEquals(response.username(), request.username());
    }

    @Test
    void save_whenUsernameExists_throwsUserAlreadyExistsException() {
        when(userJdbcRepository.findByUsername(any())).thenReturn(Optional.of(new User()));
        UserRequest request = new UserRequest("test", "", "");
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.save(request));
    }
    
    @Test
    void update_whenWrongId_throwsUserNotFoundException() {
        Long id = 1L;
        when(userJdbcRepository.findById(id)).thenReturn(Optional.empty());
        UserRequest request = new UserRequest("test", "", "");
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.update(id, request));
    }
    
    @Test
    void update_whenUsernameAlreadyExists_throwsUserAlreadyExistsException() {
        Long id = 1L;
        when(userJdbcRepository.findById(id)).thenReturn(Optional.of(new User()));
        UserRequest request = new UserRequest("test", "", "");
        User exists = User.builder().id(2L).username("test").build();
        when(userJdbcRepository.findByUsername(request.username())).thenReturn(Optional.of(exists));
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.update(id, request));
    }
    
    @Test
    void update_whenCorrectUsernameAndId_returnsUpdatedUser() throws UserNotFoundException, UserAlreadyExistsException {
        Long id = 1L;
        when(userJdbcRepository.findById(id)).thenReturn(Optional.of(new User()));
        UserRequest request = new UserRequest("test", "", "");
        when(userJdbcRepository.findByUsername(request.username())).thenReturn(Optional.empty());
        UserResponse result = userService.update(id, request);
        verify(userJdbcRepository, times(1)).update(any());
        Assertions.assertEquals(request.username(), result.username());
    }
    
    @Test
    void delete_whenWrongId_throwsUserNotFoundException() {
        Long id = 1L;
        when(userJdbcRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.delete(id));
    }
    
    @Test
    void delete_whenUserExists_deleteUser() throws UserNotFoundException {
        Long id = 1L;
        when(userJdbcRepository.findById(id)).thenReturn(Optional.of(new User()));
        userService.delete(id);
        verify(userJdbcRepository, times(1)).delete(any());
    }
}
