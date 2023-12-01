package com.shop.controller;

import com.shop.model.dto.ProductDto;
import com.shop.service.BasketProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с товарами в корзине
 */
@Slf4j
@RestController
@RequestMapping(value = "api/v1/baskets/buy")
@Tag(name = "Работа с корзиной")
@RequiredArgsConstructor
public class BuyController {

    private final BasketProductService service;

    @PostMapping
    @Operation(summary = "Положить товар в корзину")
    @ResponseStatus(HttpStatus.CREATED)
    public void putProducts(@RequestParam String basketId,
                            @RequestParam String productId) {
        log.info("Добавление в корзину {} товара с id {}", basketId, productId);
        service.putProduct(basketId, productId);
    }

    @GetMapping
    @Operation(summary = "Показать все товары из корзины")
    public List<ProductDto> getProducts(@RequestParam String basketId) {
        log.info("Список всех товаров из корзины с id {}", basketId);
        return service.allProducts(basketId);
    }

    @DeleteMapping
    @Operation(summary = "Удалить товар из корзины")
    public void deleteProduct(@RequestParam String basketId,
                              @RequestParam String productId) {
        log.info("Удаление из корзины {} товара с id {}", basketId, productId);
        service.deleteProduct(basketId, productId);
    }
}
