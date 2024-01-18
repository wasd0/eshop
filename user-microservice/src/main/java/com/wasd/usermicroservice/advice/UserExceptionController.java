package com.wasd.usermicroservice.advice;

import com.wasd.usermicroservice.data.exception.HttpExceptionResponse;
import com.wasd.usermicroservice.exception.UserAlreadyExistsException;
import com.wasd.usermicroservice.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpExceptionResponse userNotFound(UserNotFoundException exception) {
        return new HttpExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpExceptionResponse userAlreadyExists(UserAlreadyExistsException exception) {
        return new HttpExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
    }
}
