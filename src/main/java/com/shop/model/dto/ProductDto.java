package com.shop.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "не должно быть пустым")
    private String title;

    @NotBlank(message = "не должно быть пустым")
    private String description;

    @Positive(message = "должно быть больше 0")
    private double price;

    @Override
    public String toString() {
        return "Название='" + title + '\'' +
                ", Описание='" + description + '\'' +
                ", Цена за ед.=" + price;
    }
}