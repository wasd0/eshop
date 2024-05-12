package com.wasd.productmicroservice.advice;

import com.wasd.productmicroservice.data.exception.HttpExceptionResponse;
import com.wasd.productmicroservice.exception.common.AlreadyExistsException;
import com.wasd.productmicroservice.exception.persistence.CategoryCreationException;
import com.wasd.productmicroservice.exception.persistence.SellerCreationException;
import com.wasd.productmicroservice.exception.persistence.WarehouseOperationCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler({AlreadyExistsException.class,
        SellerCreationException.class,
        CategoryCreationException.class,
        WarehouseOperationCreationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpExceptionResponse orderCreation(Exception exception) {
        return new HttpExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
    }

}
