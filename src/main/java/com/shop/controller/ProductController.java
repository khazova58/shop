package com.shop.controller;

import com.shop.model.dto.ProductDto;
import com.shop.service.contract.ProductContract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с сущностью Покупка
 */
@Slf4j
@RestController
@RequestMapping(value = "api/v1/products")
@Tag(name = "Товар")
@RequiredArgsConstructor
public class ProductController {

    private final ProductContract service;

    @PostMapping
    @Operation(summary = "Добавить новый товар")
    @ResponseStatus(HttpStatus.CREATED)
    public String newProduct(@Valid @RequestBody ProductDto dto) {
        log.info("Создание нового товара: {}", dto);
        return service.newProduct(dto);
    }

    @GetMapping
    @Operation(summary = "Найти товар по id")
    public ProductDto getProduct(@RequestParam String id) {
        log.info("Поиск товара с id {}", id);
        return service.getProduct(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Все товары")
    public List<ProductDto> allProducts() {
        log.info("Получение списка всех товаров из базы данных");
        return service.allProducts();
    }

    @PutMapping
    @Operation(summary = "Обновить товар с заданным id")
    public ProductDto updateProduct(@RequestParam String id,
                                    @Valid @RequestBody ProductDto dto) {
        log.info("Изменение карточки товара с id {}", id);
        return service.updateProduct(id, dto);
    }

    @DeleteMapping
    @Operation(summary = "Удалить товар с заданным id")
    public void deleteProduct(@RequestParam String id) {
        log.info("Удаление товара с id {}", id);
        service.deleteProduct(id);
    }
}