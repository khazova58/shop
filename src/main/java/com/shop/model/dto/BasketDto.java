package com.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BasketDto {

    private String basketId;
    private LocalDateTime dateCreation;
}