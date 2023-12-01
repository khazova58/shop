package com.shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessError {
    BASKET_NOT_FOUND("00000", "Корзина с id: %s отсутствует в базе", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("00001", "Товар с id: %s отсутствует в базе", HttpStatus.NOT_FOUND);

    private final String errorCode;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessError(String errorCode, String description, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}