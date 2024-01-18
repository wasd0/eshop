package com.wasd.usermicroservice.data.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record HttpExceptionResponse(HttpStatus status, String message, LocalDateTime dateTime) {
}
