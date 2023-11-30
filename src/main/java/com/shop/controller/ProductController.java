package com.shop.controller;

import com.shop.model.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с сущностью Покупка
 */
@RestController
@RequestMapping(value = "api/v1/products")
@Tag(name = "Товар")
public class ProductController {

    @PostMapping
    @Operation(summary = "Добавить новый товар")
    public String newProduct(@RequestBody ProductDto dto) {
        return null;
    }

    @GetMapping
    @Operation(summary = "Найти товар по id")
    public ProductDto getProduct(@RequestParam String id) {
        return null;
    }

    @GetMapping("/all")
    @Operation(summary = "Список всех товаров")
    public List<ProductDto> allProducts() {
        return null;
    }

    @PutMapping
    @Operation(summary = "Обновить товар с заданным id")
    public ProductDto updateProduct(@RequestParam String id, @RequestBody ProductDto dto) {
        return null;
    }

    @DeleteMapping
    @Operation(summary = "Удалить товар с заданным id")
    public void deleteProduct(@RequestParam String id) {
    }
}