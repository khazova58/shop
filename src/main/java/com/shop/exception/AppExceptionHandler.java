package com.shop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
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
        log.error("Данные не найдены: {}", error.getDescription());
        return new ResponseEntity<>(error, ex.getBusinessError().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> errorList = bindingResult.getFieldErrors();
        List<String> messages = errorList.stream()
                .map(error -> "Поле " + error.getField() + " " + error.getDefaultMessage())
                .toList();
        ApiError apiError = ApiError.builder()
                .errorCode("Error:0002")
                .description("Ошибка валидации")
                .timeStamp(LocalDateTime.now().format(formatter))
                .errors(messages)
                .build();
        log.error("Ошибка валидации: {}", messages);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ApiError error = ApiError.builder()
                .errorCode("Error:0003")
                .description(ex.getMessage())
                .timeStamp(LocalDateTime.now().format(formatter))
                .build();
        log.error("Exception from Runtime: {}", error.getDescription());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}