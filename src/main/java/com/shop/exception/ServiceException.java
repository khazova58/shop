package com.shop.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private final BusinessError businessError;

    public ServiceException(BusinessError businessError, Object... args) {
        super(businessError.getDescription().formatted(args));
        this.businessError = businessError;
    }
}