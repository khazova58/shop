package com.shop.controller;

import com.shop.model.dto.ProductIdDto;
import com.shop.model.dto.ProductDto;
import com.shop.service.contract.ProductContract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
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
    public ProductIdDto newProduct(@Valid @RequestBody ProductDto dto) {
        log.info("Создание нового товара: {}", dto);
        return service.newProduct(dto);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Найти товар по id")
    public ProductDto getProduct(@PathVariable(name = "productId") String productId) {
        log.info("Поиск товара с id {}", productId);
        return service.getProduct(productId);
    }

    @GetMapping
    @Operation(summary = "Все товары")
    public List<ProductDto> allProducts(@ParameterObject Pageable pageable) {
        log.info("Получение списка всех товаров из базы данных");
        return service.allProducts(pageable);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Обновить товар с заданным id")
    public ProductDto updateProduct(@PathVariable(name = "productId") String productId,
                                    @Valid @RequestBody ProductDto dto) {
        log.info("Изменение карточки товара с id {}", productId);
        return service.updateProduct(productId, dto);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Удалить товар с заданным id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "productId") String productId) {
        log.info("Удаление товара с id {}", productId);
        service.deleteProduct(productId);
    }
}