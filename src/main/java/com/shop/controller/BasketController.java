package com.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с Корзиной покупок
 */
@RestController
@RequestMapping(value = "api/v1/baskets")
@Tag(name = "Корзина")
public class BasketController {

    @PostMapping
    @Operation(summary = "Положить товар в корзину")
    public void newBasket() {
    }

    @GetMapping
    @Operation(summary = "Отобразить все товары из корзины")
    public void getProducts() {
    }

    @PutMapping
    @Operation(summary = "Редактировать количество товара в корзине")
    public void updateCountProduct() {
    }

    @DeleteMapping
    @Operation(summary = "Удалить товар из корзины")
    public void deleteProduct() {
    }
}
