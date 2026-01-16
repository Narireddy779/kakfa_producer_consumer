package com.example.kafka_producer.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable throwable) {
        return ResponseEntity
                .internalServerError()
                .body(Map.of(
                        "error", throwable.getClass().getSimpleName(),
                        "message", throwable.getMessage()
                ));
    }
}
