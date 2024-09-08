package com.my.user.hotel.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(ResourceNotFoundException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message", ex.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND.value()); // Use the value of the HttpStatus enum

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
