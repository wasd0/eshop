package com.wasd.ordermicroservice.advice;

import com.wasd.ordermicroservice.data.exception.HttpExceptionResponse;
import com.wasd.ordermicroservice.exception.common.AlreadyExistsException;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.exception.persistence.OrderCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpExceptionResponse notFound(NotFoundException exception) {
        return new HttpExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler({OrderCreationException.class, AlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpExceptionResponse orderCreation(Exception exception) {
        return new HttpExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
    }
}
