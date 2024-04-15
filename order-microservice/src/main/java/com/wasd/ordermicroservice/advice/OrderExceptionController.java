package com.wasd.ordermicroservice.advice;

import com.wasd.ordermicroservice.data.exception.HttpExceptionResponse;
import com.wasd.ordermicroservice.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class OrderExceptionController {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpExceptionResponse orderNotFound(OrderNotFoundException exception) {
        return new HttpExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }
}
