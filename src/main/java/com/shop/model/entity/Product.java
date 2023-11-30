package com.shop.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Покупка
 */
@Entity
@Table(name = "product", schema = "shopping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private String productId;

    /**
     * Название товара
     */
    @Column(name = "title", unique = true)
    private String title;

    /**
     * Описание товара
     */
    @Column(name = "description")
    private String description;

    /**
     * Цена за единицу
     */
    @Column(name = "price")
    private double price;
}