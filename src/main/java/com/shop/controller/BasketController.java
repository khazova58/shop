package com.shop.controller;

import com.shop.model.dto.BasketDto;
import com.shop.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с Корзиной
 */
@RestController
@RequestMapping(value = "api/v1/baskets")
@Tag(name = "Корзина")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService service;

    @PostMapping
    @Operation(summary = "Создать корзину для пользователя")
    public BasketDto newBasket() {
        return service.newBasket();
    }

    @DeleteMapping
    @Operation(summary = "Удалить корзину пользователя")
    public void deleteBasket(@RequestParam String id) {
        service.deleteBasket(id);
    }
}
