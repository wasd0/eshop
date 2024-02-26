package com.wasd.productmicroservice.advice;

import com.wasd.productmicroservice.data.exception.HttpExceptionResponse;
import com.wasd.productmicroservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ProductExceptionController {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpExceptionResponse userNotFound(ProductNotFoundException exception) {
        return new HttpExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }
}
