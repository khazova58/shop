package com.shop.controller;

import com.shop.model.dto.BasketDto;
import com.shop.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с Корзиной
 */
@Slf4j
@RestController
@RequestMapping(value = "api/v1/baskets")
@Tag(name = "Корзина")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService service;

    @PostMapping
    @Operation(summary = "Создать корзину для пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    public BasketDto newBasket() {
        log.info("Создание новой корзины");
        return service.newBasket();
    }

    @DeleteMapping
    @Operation(summary = "Удалить корзину пользователя")
    public void deleteBasket(@RequestParam String id) {
        log.info("Удаление корзины с id {}", id);
        service.deleteBasket(id);
    }
}
