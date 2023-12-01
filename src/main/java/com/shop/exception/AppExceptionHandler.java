package com.shop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class AppExceptionHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(ServiceException ex) {
        ApiError error = ApiError.builder()
                .errorCode(ex.getBusinessError().getErrorCode())
                .description(ex.getMessage())
                .timeStamp(LocalDateTime.now().format(formatter))
                .build();
        return new ResponseEntity<>(error, ex.getBusinessError().getHttpStatus());
    }
}